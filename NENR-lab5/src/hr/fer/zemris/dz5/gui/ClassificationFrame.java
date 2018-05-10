package hr.fer.zemris.dz5.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import hr.fer.zemris.dz5.neural.NeuralNet;

public class ClassificationFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	Canvas canvas = new Canvas();
	NeuralNet net;
	JLabel label = new JLabel("");

	public ClassificationFrame(NeuralNet net) {
		this.net = net;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocation(200, 100);
		setSize(640, 480);
		setTitle("Window");
		getContentPane().setLayout(new BorderLayout());
		initGUI();
	}

	private void initGUI() {
		add(canvas, BorderLayout.CENTER);
		canvas.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				canvas.points = new ArrayList<>();
				canvas.points.add(new Coordinate(e.getPoint()));
			}

			public void mouseReleased(MouseEvent e) {
				canvas.points.add(new Coordinate(e.getPoint()));
				String[] letters = { "Alpha", "Beta", "Gamma", "Delta", "Epsilon" };
				if (canvas.points.size() > 30) {
					int letterIndex = net.getIndexOfBest(
							Coordinate.getInputsFromFeatures(Coordinate.getFeatures(canvas.points, 25)));
					label.setText(letters[letterIndex]);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				canvas.repaint();
			}
		});
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				canvas.points.add(new Coordinate(e.getPoint()));
				canvas.repaint();
			}
		});
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(label.getFont().deriveFont(label.getFont().getSize2D() * 2f));
		canvas.setLayout(new BorderLayout());
		add(label, BorderLayout.NORTH);

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
}
