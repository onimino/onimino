package org.onimino.gui.reskin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.onimino.plaf.ZeroMetalTheme;

public class StandaloneLicensePanel extends JPanel {
	private JEditorPane editor;
	
	public StandaloneLicensePanel() {
		super(new BorderLayout());
		try {
			editor = new JEditorPane(StandaloneLicensePanel.class.getClassLoader().getResource("org/onimino/About.html"));
			editor.setBackground(new ZeroMetalTheme().getSecondary3());
			editor.setForeground(Color.WHITE);
			editor.setBorder(null);;
			editor.setEditable(false);
			add(editor, BorderLayout.CENTER);
		} catch(IOException ioe) {
		}
		setBorder(null);
	}
}
