package hr.fer.zemris.dz5.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class ExampleMakerFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	Canvas canvas = new Canvas();
	Writer fw;

	public ExampleMakerFrame() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					fw.close();
				} catch (IOException e1) {
				}
			}
		});
		setLocation(200, 100);
		setSize(640, 480);
		setTitle("Window");
		getContentPane().setLayout(new BorderLayout());
		initGUI();
	}

	private void initGUI() {
		try {
			fw = new BufferedWriter(new FileWriter("./examples.txt"));
		} catch (IOException e1) {
			System.out.println("Cannot open file");
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		add(canvas, BorderLayout.CENTER);
		canvas.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				canvas.points = new ArrayList<>();
				canvas.points.add(new Coordinate(e.getPoint()));
			}

			public void mouseReleased(MouseEvent e) {
				canvas.points.add(new Coordinate(e.getPoint()));
				canvas.repaint();
			}
		});
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				canvas.points.add(new Coordinate(e.getPoint()));
				canvas.repaint();
			}
		});
		addButtons();
	}

	private void addButtons() {
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		JButton[] buttons = new JButton[] { new JButton("Alpha"), new JButton("Beta"), new JButton("Gamma"),
				new JButton("Delta"), new JButton("Epsilon") };
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(new ButtonListener(i));
			buttonsPanel.add(buttons[i]);
		}
		add(buttonsPanel, BorderLayout.NORTH);

	}

	private class ButtonListener implements ActionListener {
		private int[] vector;

		public ButtonListener(int classNumber) {
			super();
			vector = new int[5];
			vector[classNumber] = 1;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				List<Coordinate> features = Coordinate.getFeatures(canvas.points, 25);
				for (Coordinate feature : features) {
					fw.write(feature.toString());
				}
				for (int i : vector) {
					fw.write(i + " ");
				}
				fw.write("\n");
				fw.flush();
			} catch (IOException e) {
			}
		}
	}

	private class Canvas extends JPanel {
		private static final long serialVersionUID = 1L;
		List<Coordinate> points = new ArrayList<>();

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (points.size() < 2 || points == null) {
				return;
			}
			int pointsSize = points.size();
			for (int i = 1; i < pointsSize; i++) {
				g.drawLine((int) points.get(i).getX(), (int) points.get(i).getY(), (int) points.get(i - 1).getX(),
						(int) points.get(i - 1).getY());
			}

		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ExampleMakerFrame().setVisible(true);
			}
		});
	}

}
