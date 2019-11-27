package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.Engine;
import Model.Bullet;
import Model.Player;
import Model.Stone;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GUI extends JFrame {

	private JPanel contentPane;
	Player p;
	Stone s;
	Bullet b;
	Panel panel;
	ObserverPanel observerpanel;
	public static boolean isPaused;
	JFileChooser fcOpen;
	Engine eng;
	public GUI() {
		eng=Engine.getEngine();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBounds(0, 0,768, 768);
		setContentPane(contentPane);
		fcOpen = new JFileChooser("C:\\");
		fcOpen.addChoosableFileFilter(new FileNameExtensionFilter( "txt",  "TXT"));
		fcOpen.setAcceptAllFileFilterUsed(false);
		isPaused= false;

		
		panel = new Panel(786,786,Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.requestFocus();
		panel.setFocusable(true);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,1400, 768);
		
		
		observerpanel=new ObserverPanel(400,768, Color.DARK_GRAY,panel);
		panel.setLayout(null);
		getContentPane().add(observerpanel, BorderLayout.EAST);
		
		JButton btnPause = new JButton("Pause");
		btnPause.setBackground(Color.CYAN);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isPaused= true;
			observerpanel.t.stop();
			contentPane.remove(panel);
			}
		});
		btnPause.setBounds(12, 375, 176, 50);
		observerpanel.add(btnPause);
		
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setBackground(Color.CYAN);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPaused= false;
				getContentPane().add(panel, BorderLayout.CENTER);
				panel.requestFocus();
				observerpanel.t.start();
				observerpanel.setVisible(true);
				setVisible(true);
			}
		});
		btnContinue.setBounds(212, 375, 176, 50);
		
		observerpanel.add(btnContinue);
		
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(Color.CYAN);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPaused= true;
				observerpanel.t.stop();
				contentPane.remove(panel);
				if(fcOpen.showSaveDialog(panel) == JFileChooser.APPROVE_OPTION)
		    	{
					
			    	String path=fcOpen.getSelectedFile().getPath();
			    	path+="."+fcOpen.getFileFilter().getDescription().toLowerCase();
			    	eng.save(path,observerpanel.getCount());
			    	isPaused= false;
					getContentPane().add(panel, BorderLayout.CENTER);
					panel.requestFocus();
					observerpanel.t.start();
					observerpanel.setVisible(true);
					setVisible(true);
				
			}}
		});
		btnSave.setBounds(12, 466, 176, 50);
		
		observerpanel.add(btnSave);
		
		JButton btnLoad = new JButton("Load\r\n");
		btnLoad.setBackground(Color.CYAN);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fcOpen.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION)
		    	{
			    	String path=fcOpen.getSelectedFile().getPath();
			      	File f=new File(path);
			    	if(!f.exists())
			    		System.out.println("not found"); // show error message
			    	else observerpanel.setCount(eng.load(path));
			    	panel.requestFocus();
			    	panel.setFocusable(true);
			}}
		});
		btnLoad.setBounds(212, 466, 176, 50);
		observerpanel.add(btnLoad);
		

JButton btnRestart = new JButton("Restart");
		btnRestart.setBackground(Color.CYAN);
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPaused= false;
				getContentPane().add(panel, BorderLayout.CENTER);
				panel.requestFocus();
				eng.getP().setHealth(100);
				eng.getP().setBullets(6);
				eng.getP().setScore(0);
				Point poi=new Point(24, 24);
				eng.getP().setPosition(poi);
				eng.getP().setDirection("right");
				observerpanel.t.restart();
				observerpanel.setVisible(true);
				setVisible(true);
			}
		});
		btnRestart.setBounds(118, 546, 176, 50);
		
		observerpanel.add(btnRestart);
		
		
	        setVisible(true);
	}
}