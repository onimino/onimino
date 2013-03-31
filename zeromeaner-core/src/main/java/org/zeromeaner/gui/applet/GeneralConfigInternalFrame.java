/*
    Copyright (c) 2010, NullNoname
    All rights reserved.

    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions are met:

        * Redistributions of source code must retain the above copyright
          notice, this list of conditions and the following disclaimer.
        * Redistributions in binary form must reproduce the above copyright
          notice, this list of conditions and the following disclaimer in the
          documentation and/or other materials provided with the distribution.
        * Neither the name of NullNoname nor the names of its
          contributors may be used to endorse or promote products derived from
          this software without specific prior written permission.

    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
    AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
    IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
    ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
    LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
    CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
    SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
    INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
    CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
    POSSIBILITY OF SUCH DAMAGE.
*/
package org.zeromeaner.gui.applet;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * Setting screen frame
 */
public class GeneralConfigInternalFrame extends JInternalFrame implements ActionListener {
	/** Serial version ID */
	private static final long serialVersionUID = 1L;

	/** Screen size table */
	protected static final int[][] SCREENSIZE_TABLE =
	{
		{320,240}, {400,300}, {480,360}, {512,384}, {640,480}, {800,600}, {1024,768}, {1152,864}, {1280,960}
	};

	/** Parent window */
	protected NullpoMinoInternalFrame owner;

	/** Model of screen size combobox */
	protected DefaultComboBoxModel modelScreenSize;

	/** Screen size combobox */
	protected JComboBox comboboxScreenSize;

	/** MaximumFPS */
	protected JTextField txtfldMaxFPS;

	/** Sound effectsVolume of */
	protected JTextField txtfldSEVolume;

	/** Line clear effect speed */
	protected JTextField txtfldLineClearEffectSpeed;

	/** FPSDisplay */
	protected JCheckBox chkboxShowFPS;

	/** BackgroundDisplay */
	protected JCheckBox chkboxShowBackground;

	/** MeterDisplay */
	protected JCheckBox chkboxShowMeter;

	/** fieldOfBlockDisplay a picture of a ( check Only if there is no border) */
	protected JCheckBox chkboxShowFieldBlockGraphics;

	/** Simple picture ofBlockI use */
	protected JCheckBox chkboxSimpleBlock;

	/** Sound effects */
	protected JCheckBox chkboxSE;

	/** NativeLook and FeelI use */
	protected JCheckBox chkboxUseNativeLookAndFeel;

	/**  frame Step */
	protected JCheckBox chkboxEnableFrameStep;

	/** ghost On top of the pieceNEXTDisplay */
	protected JCheckBox chkboxNextShadow;

	/** Linear frameghost Peace */
	protected JCheckBox chkboxOutlineGhost;

	/** Side piece preview */
	protected JCheckBox chkboxSideNext;

	/** Use bigger side piece preview */
	protected JCheckBox chkboxBigSideNext;

	/** Perfect FPS */
	protected JCheckBox chkboxPerfectFPSMode;

	/** Execute Thread.yield() during Perfect FPS mode */
	protected JCheckBox chkboxPerfectYield;

	/** Sync Display */
	protected JCheckBox chkboxSyncDisplay;

	/** Show line clear effect */
	protected JCheckBox chkboxShowLineClearEffect;

	/** Dark piece preview area */
	protected JCheckBox chkboxDarkNextArea;

	/** Show field BG grid */
	protected JCheckBox chkboxShowFieldBGGrid;

	/** Show field BG grid */
	protected JCheckBox chkboxShowInput;

	/**
	 * Constructor
	 * @param owner Parent window
	 * @throws HeadlessException Keyboard, Mouse, Exceptions such as the display if there is no
	 */
	public GeneralConfigInternalFrame(NullpoMinoInternalFrame owner) throws HeadlessException {
		super();
		this.owner = owner;

		// GUIOfInitialization
		setTitle(NullpoMinoInternalFrame.lz.s("Title_GeneralConfig"));
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setResizable(false);
		initUI();
		pack();
		AppletMain.instance.desktop.add(this);
	}

	/**
	 * GUIOfInitialization
	 */
	protected void initUI() {
		this.getContentPane().setLayout(new BorderLayout());

		// * Tab pane
		JTabbedPane tabPane = new JTabbedPane();
		this.add(tabPane, BorderLayout.CENTER);

		// ** Basic Tab
		JPanel pBasicTab = new JPanel();
		pBasicTab.setLayout(new BoxLayout(pBasicTab, BoxLayout.Y_AXIS));
		tabPane.addTab(NullpoMinoInternalFrame.lz.s("GeneralConfig_TabName_Basic"), pBasicTab);

		// ---------- Sound effectsVolume of ----------
		JPanel pSEVolume = new JPanel();
		pSEVolume.setAlignmentX(LEFT_ALIGNMENT);
		pBasicTab.add(pSEVolume);

		JLabel lSEVolume = new JLabel(NullpoMinoInternalFrame.lz.s("GeneralConfig_SEVolume"));
		pSEVolume.add(lSEVolume);

		txtfldSEVolume = new JTextField(5);
		pSEVolume.add(txtfldSEVolume);

		// ---------- checkBox ----------
		chkboxShowBackground = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_ShowBackground"));
		chkboxShowBackground.setAlignmentX(LEFT_ALIGNMENT);
		pBasicTab.add(chkboxShowBackground);

		chkboxShowMeter = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_ShowMeter"));
		chkboxShowMeter.setAlignmentX(LEFT_ALIGNMENT);
		pBasicTab.add(chkboxShowMeter);

		chkboxShowFieldBlockGraphics = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_ShowFieldBlockGraphics"));
		chkboxShowFieldBlockGraphics.setAlignmentX(LEFT_ALIGNMENT);
		pBasicTab.add(chkboxShowFieldBlockGraphics);

		chkboxSimpleBlock = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_SimpleBlock"));
		chkboxSimpleBlock.setAlignmentX(LEFT_ALIGNMENT);
		pBasicTab.add(chkboxSimpleBlock);

		chkboxSE = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_SE"));
		chkboxSE.setAlignmentX(LEFT_ALIGNMENT);
		pBasicTab.add(chkboxSE);

		chkboxNextShadow = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_NextShadow"));
		chkboxNextShadow.setAlignmentX(LEFT_ALIGNMENT);
		pBasicTab.add(chkboxNextShadow);

		chkboxOutlineGhost = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_OutlineGhost"));
		chkboxOutlineGhost.setAlignmentX(LEFT_ALIGNMENT);
		pBasicTab.add(chkboxOutlineGhost);

		chkboxSideNext = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_SideNext"));
		chkboxSideNext.setAlignmentX(LEFT_ALIGNMENT);
		pBasicTab.add(chkboxSideNext);

		chkboxBigSideNext = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_BigSideNext"));
		chkboxBigSideNext.setAlignmentX(LEFT_ALIGNMENT);
		pBasicTab.add(chkboxBigSideNext);

		chkboxDarkNextArea = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_DarkNextArea"));
		chkboxDarkNextArea.setAlignmentX(LEFT_ALIGNMENT);
		pBasicTab.add(chkboxDarkNextArea);

		chkboxShowFieldBGGrid = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_ShowFieldBGGrid"));
		chkboxShowFieldBGGrid.setAlignmentX(LEFT_ALIGNMENT);
		pBasicTab.add(chkboxShowFieldBGGrid);

		chkboxShowInput = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_ShowInput"));
		chkboxShowInput.setAlignmentX(LEFT_ALIGNMENT);
		pBasicTab.add(chkboxShowInput);

		// ** Advanced Tab
		JPanel pAdvancedTab = new JPanel();
		pAdvancedTab.setLayout(new BoxLayout(pAdvancedTab, BoxLayout.Y_AXIS));
		tabPane.addTab(NullpoMinoInternalFrame.lz.s("GeneralConfig_TabName_Advanced"), pAdvancedTab);

		// ---------- Screen size ----------
		JPanel pScreenSize = new JPanel();
		pScreenSize.setAlignmentX(LEFT_ALIGNMENT);
		pAdvancedTab.add(pScreenSize);

		JLabel lScreenSize = new JLabel(NullpoMinoInternalFrame.lz.s("GeneralConfig_ScreenSize"));
		pScreenSize.add(lScreenSize);

		modelScreenSize = new DefaultComboBoxModel();
		for(int i = 0; i < SCREENSIZE_TABLE.length; i++) {
			String strTemp = SCREENSIZE_TABLE[i][0] + "x" + SCREENSIZE_TABLE[i][1];
			modelScreenSize.addElement(strTemp);
		}
		comboboxScreenSize = new JComboBox(modelScreenSize);
		pScreenSize.add(comboboxScreenSize);

		// ---------- MaximumFPS ----------
		JPanel pMaxFPS = new JPanel();
		pMaxFPS.setAlignmentX(LEFT_ALIGNMENT);
		pAdvancedTab.add(pMaxFPS);

		JLabel lMaxFPS = new JLabel(NullpoMinoInternalFrame.lz.s("GeneralConfig_MaxFPS"));
		pMaxFPS.add(lMaxFPS);

		txtfldMaxFPS = new JTextField(5);
		pMaxFPS.add(txtfldMaxFPS);

		// ---------- Line clear effect speed ----------
		JPanel pLineClearEffectSpeed = new JPanel();
		pLineClearEffectSpeed.setAlignmentX(LEFT_ALIGNMENT);
		pAdvancedTab.add(pLineClearEffectSpeed);

		JLabel lLineClearEffectSpeed = new JLabel(NullpoMinoInternalFrame.lz.s("GeneralConfig_LineClearEffectSpeed"));
		pLineClearEffectSpeed.add(lLineClearEffectSpeed);

		txtfldLineClearEffectSpeed = new JTextField(5);
		pLineClearEffectSpeed.add(txtfldLineClearEffectSpeed);

		// ---------- Checkboxes ----------
		chkboxShowFPS = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_ShowFPS"));
		chkboxShowFPS.setAlignmentX(LEFT_ALIGNMENT);
		pAdvancedTab.add(chkboxShowFPS);

		chkboxUseNativeLookAndFeel = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_UseNativeLookAndFeel"));
		chkboxUseNativeLookAndFeel.setAlignmentX(LEFT_ALIGNMENT);
		pAdvancedTab.add(chkboxUseNativeLookAndFeel);

		chkboxEnableFrameStep = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_EnableFrameStep"));
		chkboxEnableFrameStep.setAlignmentX(LEFT_ALIGNMENT);
		pAdvancedTab.add(chkboxEnableFrameStep);

		chkboxPerfectFPSMode = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_PerfectFPSMode"));
		chkboxPerfectFPSMode.setAlignmentX(LEFT_ALIGNMENT);
		pAdvancedTab.add(chkboxPerfectFPSMode);

		chkboxPerfectYield = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_PerfectYield"));
		chkboxPerfectYield.setAlignmentX(LEFT_ALIGNMENT);
		pAdvancedTab.add(chkboxPerfectYield);

		chkboxSyncDisplay = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_SyncDisplay"));
		chkboxSyncDisplay.setAlignmentX(LEFT_ALIGNMENT);
		pAdvancedTab.add(chkboxSyncDisplay);

		chkboxShowLineClearEffect = new JCheckBox(NullpoMinoInternalFrame.lz.s("GeneralConfig_ShowLineClearEffect"));
		chkboxShowLineClearEffect.setAlignmentX(LEFT_ALIGNMENT);
		pAdvancedTab.add(chkboxShowLineClearEffect);

		// ---------- The bottom of the screen button ----------
		JPanel pButtons = new JPanel();
		pButtons.setAlignmentX(LEFT_ALIGNMENT);
		this.add(pButtons, BorderLayout.SOUTH);

		JButton buttonOK = new JButton(NullpoMinoInternalFrame.lz.s("GeneralConfig_OK"));
		buttonOK.setMnemonic('O');
		buttonOK.addActionListener(this);
		buttonOK.setActionCommand("GeneralConfig_OK");
		pButtons.add(buttonOK);

		JButton buttonCancel = new JButton(NullpoMinoInternalFrame.lz.s("GeneralConfig_Cancel"));
		buttonCancel.setMnemonic('C');
		buttonCancel.addActionListener(this);
		buttonCancel.setActionCommand("GeneralConfig_Cancel");
		pButtons.add(buttonCancel);
	}

	/**
	 * Current SettingsGUIBe reflected in the
	 */
	public void load() {
		int sWidth = NullpoMinoInternalFrame.propConfig.getProperty("option.screenwidth", 640);
		int sHeight = NullpoMinoInternalFrame.propConfig.getProperty("option.screenheight", 480);
		comboboxScreenSize.setSelectedIndex(4);	// Default to 640x480
		for(int i = 0; i < SCREENSIZE_TABLE.length; i++) {
			if((sWidth == SCREENSIZE_TABLE[i][0]) && (sHeight == SCREENSIZE_TABLE[i][1])) {
				comboboxScreenSize.setSelectedIndex(i);
				break;
			}
		}

		txtfldMaxFPS.setText(String.valueOf(NullpoMinoInternalFrame.propConfig.getProperty("option.maxfps", 60)));
		txtfldSEVolume.setText(String.valueOf(NullpoMinoInternalFrame.propConfig.getProperty("option.sevolume", 1.0d)));
		txtfldLineClearEffectSpeed.setText(String.valueOf(NullpoMinoInternalFrame.propConfig.getProperty("option.lineeffectspeed", 0) + 1));
		chkboxShowFPS.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.showfps", true));
		chkboxShowBackground.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.showbg", true));
		chkboxShowMeter.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.showmeter", true));
		chkboxShowFieldBlockGraphics.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.showfieldblockgraphics", true));
		chkboxSimpleBlock.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.simpleblock", false));
		chkboxSE.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.se", true));
		chkboxUseNativeLookAndFeel.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.usenativelookandfeel", true));
		chkboxEnableFrameStep.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.enableframestep", false));
		chkboxNextShadow.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.nextshadow", false));
		chkboxOutlineGhost.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.outlineghost", false));
		chkboxSideNext.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.sidenext", false));
		chkboxBigSideNext.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.bigsidenext", false));
		chkboxDarkNextArea.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.darknextarea", true));
		chkboxShowFieldBGGrid.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.showfieldbggrid", true));
		chkboxShowInput.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.showInput", false));
		chkboxPerfectFPSMode.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.perfectFPSMode", false));
		chkboxPerfectYield.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.perfectYield", true));
		chkboxSyncDisplay.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.syncDisplay", true));
		chkboxShowLineClearEffect.setSelected(NullpoMinoInternalFrame.propConfig.getProperty("option.showlineeffect", false));
	}

	/*
	 *  Called when button clicked
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "GeneralConfig_OK") {
			// OK
			int screenSizeIndex = comboboxScreenSize.getSelectedIndex();
			if((screenSizeIndex >= 0) && (screenSizeIndex < SCREENSIZE_TABLE.length)) {
				NullpoMinoInternalFrame.propConfig.setProperty("option.screenwidth", SCREENSIZE_TABLE[screenSizeIndex][0]);
				NullpoMinoInternalFrame.propConfig.setProperty("option.screenheight", SCREENSIZE_TABLE[screenSizeIndex][1]);
			}

			int maxfps = NullpoMinoInternalFrame.getIntTextField(60, txtfldMaxFPS);
			NullpoMinoInternalFrame.propConfig.setProperty("option.maxfps", maxfps);

			double sevolume = NullpoMinoInternalFrame.getDoubleTextField(1.0d, txtfldSEVolume);
			NullpoMinoInternalFrame.propConfig.setProperty("option.sevolume", sevolume);

			int lineeffectspeed = NullpoMinoInternalFrame.getIntTextField(0, txtfldLineClearEffectSpeed) - 1;
			if(lineeffectspeed < 0) lineeffectspeed = 0;
			NullpoMinoInternalFrame.propConfig.setProperty("option.lineeffectspeed", lineeffectspeed);

			NullpoMinoInternalFrame.propConfig.setProperty("option.showfps", chkboxShowFPS.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.showbg", chkboxShowBackground.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.showmeter", chkboxShowMeter.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.showfieldblockgraphics", chkboxShowFieldBlockGraphics.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.simpleblock", chkboxSimpleBlock.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.se", chkboxSE.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.usenativelookandfeel", chkboxUseNativeLookAndFeel.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.enableframestep", chkboxEnableFrameStep.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.nextshadow", chkboxNextShadow.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.outlineghost", chkboxOutlineGhost.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.sidenext", chkboxSideNext.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.bigsidenext", chkboxBigSideNext.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.darknextarea", chkboxDarkNextArea.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.showfieldbggrid", chkboxShowFieldBGGrid.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.showInput", chkboxShowInput.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.perfectFPSMode", chkboxPerfectFPSMode.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.perfectYield", chkboxPerfectYield.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.syncDisplay", chkboxSyncDisplay.isSelected());
			NullpoMinoInternalFrame.propConfig.setProperty("option.showlineeffect", chkboxShowLineClearEffect.isSelected());

			NullpoMinoInternalFrame.saveConfig();
			ResourceHolderApplet.soundManager.setVolume(sevolume);
			if(chkboxShowBackground.isSelected()) {
				ResourceHolderApplet.loadBackgroundImages();
			}
			if(chkboxShowLineClearEffect.isSelected()) {
				ResourceHolderApplet.loadLineClearEffectImages();
			}
			this.setVisible(false);
		}
		else if(e.getActionCommand() == "GeneralConfig_Cancel") {
			// Cancel
			this.setVisible(false);
		}
	}
}
