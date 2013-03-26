package org.zeromeaner.gui.knet;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.funcish.core.Predicates;
import org.funcish.core.fn.Mappicator;
import org.funcish.core.fn.Predicate;
import org.funcish.core.impl.AbstractMappicator;
import org.funcish.core.impl.AbstractPredicate;
import org.zeromeaner.game.knet.obj.KNetChannelInfo;
import org.zeromeaner.game.subsystem.mode.AbstractNetMode;
import org.zeromeaner.game.subsystem.mode.AbstractNetVSMode;
import org.zeromeaner.game.subsystem.mode.GameMode;
import org.zeromeaner.gui.tool.RuleEditorPanel;
import org.zeromeaner.util.GeneralUtil;
import org.zeromeaner.util.LstResourceMap;
import org.zeromeaner.util.ModeList;


public class KNetChannelInfoPanel extends JPanel {
	private KNetChannelInfo channel;
	
	private JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
	
	private JTextField name = new JTextField("");
	private JSpinner maxPlayers = new JSpinner(new SpinnerNumberModel(2, 1, 6, 1)); {{
		maxPlayers.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				DefaultComboBoxModel model = (DefaultComboBoxModel) mode.getModel();
				model.removeAllElements();
				mode.setModel(model);
				Predicate<GameMode> vs = ModeList.IS_VSMODE;
				if(1 == (Integer)KNetChannelInfoPanel.this.maxPlayers.getValue())
					vs = Predicates.not(vs);
				for(String modeName : ModeList.getModes().getIsNetplay(true).filter(vs).names()) {
					model.addElement(modeName);
				}
				mode.setSelectedIndex(0);
			}
		});
	}}
	private JCheckBox autoStart = new JCheckBox();
	
	private JComboBox rule = new JComboBox();
	{{
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		rule.setModel(model);
	}}

	private JComboBox mode = new JComboBox();
	{{
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		mode.setModel(model);
		Predicate<GameMode> vs = ModeList.IS_VSMODE;
		if(1 == (Integer)KNetChannelInfoPanel.this.maxPlayers.getValue())
			vs = Predicates.not(vs);
		for(String modeName : ModeList.getModes().getIsNetplay(true).filter(vs).map(ModeList.MODE_NAME)) {
			model.addElement(modeName);
		}
		mode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel ruleModel = (DefaultComboBoxModel) rule.getModel();
				ruleModel.removeAllElements();
				
				if(mode.getSelectedItem() == null)
					return;
				
				LstResourceMap recdRules = new LstResourceMap("config/list/recommended_rules.lst");
				List<String> ruleResources = recdRules.get(mode.getSelectedItem());
				if(ruleResources == null)
					return;
				Mappicator<String, String> RULE_NAME = new AbstractMappicator<String, String>(String.class, String.class) {
					@Override
					public String map0(String obj, Integer index) {
						return GeneralUtil.loadRule(obj).strRuleName;
					}
				};
				for(String ruleName : RULE_NAME.map(ruleResources)) {
					ruleModel.addElement(ruleName);
				}
			}
		});
		mode.setSelectedIndex(0);
	}}
	
	private JCheckBox syncPlay = new JCheckBox();
	
	private RuleEditorPanel ruleEditor = new RuleEditorPanel();
	
	public KNetChannelInfoPanel(KNetChannelInfo channel) {
		this.channel = channel;

		setLayout(new BorderLayout());
		add(tabs, BorderLayout.CENTER);
		
		JPanel p;
		
		p = new JPanel(new GridLayout(0, 2));
		p.add(new JLabel("Channel Name:")); p.add(name);
		p.add(new JLabel("Max Players:")); p.add(maxPlayers);
		p.add(new JLabel("Automatic Start:")); p.add(autoStart);
		p.add(new JLabel("Game Mode:")); p.add(mode);
		p.add(new JLabel("Game Mode Rule:")); p.add(rule);
		p.add(new JLabel("Synchronous Play:")); p.add(syncPlay);
		tabs.addTab("General", p);
		
		for(int i = 0; i < ruleEditor.getTabPane().getTabCount(); i++) {
			tabs.addTab(ruleEditor.getTabPane().getTitleAt(i), new JScrollPane(ruleEditor.getTabPane().getComponentAt(i)));
		}
	}
	
	public void updateChannel() {
		channel.setName(name.getText());
		channel.setMode((String) mode.getSelectedItem());
	}
	
	public void updateEditor() {
		name.setText(channel.getName());
		mode.setSelectedItem(channel.getMode());
	}
}
