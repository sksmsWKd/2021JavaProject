package blockGame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainGame extends JFrame {
	static int state = 0;
	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("./image/����2.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("./image/����.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("./image/���۹�ư1.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("./image/���۹�ư2.png"));
	private ImageIcon soloButtonEnteredImage = new ImageIcon(Main.class.getResource("./image/�ַ�2.png"));
	private ImageIcon soloButtonBasicImage = new ImageIcon(Main.class.getResource("./image/�ַ�1.png"));
	private ImageIcon duoButtonEnteredImage = new ImageIcon(Main.class.getResource("./image/���1.png"));
	private ImageIcon duoButtonBasicImage = new ImageIcon(Main.class.getResource("./image/���2.png"));
	private JPanel img = new JPanel() {
		Image background = new ImageIcon(Main.class.getResource("./image/����2.png")).getImage();

		public void paint(Graphics g) {// �׸��� �Լ�
			g.drawImage(background, 0, 0, null);// �̹����� �׷���
		}
	};
	private JPanel img2 = new JPanel() {
		Image background = new ImageIcon(Main.class.getResource("./image/����.png")).getImage();

		public void paint(Graphics g) {// �׸��� �Լ�
			g.drawImage(background, 0, 0, null);// �̹����� �׷���
		}
	};

	private JTextField text = new JTextField(15);

	private Image Background = new ImageIcon(Main.class.getResource("./image/����ȭ��.png")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("./image/��ܹ�2.png")));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);

	private JButton soloButton = new JButton(soloButtonEnteredImage);
	private JButton duoButton = new JButton(duoButtonBasicImage);

	private int mouseX, mouseY;

	public static String userName;

	public MainGame() {

		setUndecorated(true);
		setTitle("Game");// Ÿ��Ʋ
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);// �������� ũ��
		setResizable(false);// â�� ũ�⸦ �������� ���ϰ�
		setLocationRelativeTo(null);// â�� ��� ������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame�� ���������� ����ǰ�
		setVisible(true);// â�� ���̰�
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);// ���̾ƿ��� ������� ���������ϰ� ����.

		Music introMusic = new Music("�������.mp3", true);
		introMusic.start(); // �������

		add(text);

		add(soloButton);
		add(duoButton);
		add(img);
		add(img2); // �ʿ��� �̹������� ���⼭ �� �߰��Ѵ�.
		add(startButton);
		add(exitButton);
		add(menuBar);

		img.setLayout(null);
		img.setBounds(280, 130, 939, 544);
		img.setVisible(false);

		img2.setLayout(null);
		img2.setBounds(50, 130, 939, 544);
		img2.setVisible(false); // ���� ������ ������ ��ġ ����

		soloButton.setVisible(false);
		duoButton.setVisible(false);

		text.setBounds(492, 365, 292, 67);
		text.setFont(new Font("�������", Font.BOLD, 35)); // �ؽ�Ʈ �ʵ� ��ġ �۲õ� ����
		text.setForeground(Color.white);
		text.setBackground(new Color(0, 0, 0, 122));
		text.setHorizontalAlignment(JTextField.CENTER);

		exitButton.setBounds(1245, 0, 30, 30); // �������ư ��ġ
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ���콺�̺�Ʈ�� ���콺���� ��� �̹��� ����
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// ���콺�̺�Ʈ�� ���콺���� ��� �̹����� �ٽ� ���ƿ�����
			}

			@Override
			public void mousePressed(MouseEvent e) {

				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}

				System.exit(0); // ��ư�� ������� Ŭ���� �߷�
			}
		}); // �������ư

		startButton.setBounds(515, 458, 230, 130); // ���۹�ư ��ġ
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("�ݼӹ�ư.mp3", false);
				ButtonEnteredMusic.start();// ��ư�� ������� �Ҹ������������� �Ҹ��� ������Ǻ��� �۾Ƽ� �߾ȵ鸱���� �ִ�.
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {

				if (text.getText().length() <= 0) {

					JOptionPane.showMessageDialog(null, "�г��� ���̰� �ʹ� ª���ϴ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

				} else if (text.getText().length() >= 6) {
					JOptionPane.showMessageDialog(null, "�г��� ���̰� �ʹ� ��ϴ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				} else {
					// ���� �ؽ�Ʈ�ʵ忡 ���� �г��� ���̰� �ʹ����ų� ��� ����â�� �߻�
					Music ButtonEnteredMusic = new Music("�ݼӹ�ư.mp3", false);
					ButtonEnteredMusic.start();

					userName = text.getText(); // �ؽ�Ʈ �ʵ忡 �Է� �� ���� userName�� �����̵ȴ�.

					startButton.setVisible(false);
					text.setVisible(false);
					duoButton.setVisible(true);
					soloButton.setVisible(true); // ��ư�� ������ ��ư�� ������� ���ο��ư���� �߰�
					Background = new ImageIcon(Main.class.getResource("./image/ȭ��2.png")).getImage();

				}
			}
		});// ���۹�ư

		soloButton.setBounds(50, 150, 500, 450); // �ַ� ��ġ

		soloButton.setBorderPainted(false);
		soloButton.setContentAreaFilled(false);
		soloButton.setFocusPainted(false);
		soloButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				img.setVisible(true);
				duoButton.setVisible(false);

				soloButton.setIcon(soloButtonEnteredImage);
				soloButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("�ݼӹ�ư.mp3", false);
				ButtonEnteredMusic.start();

			} // �ַ� ��� ��ư�� ������ �������̹����� ������� ���Ӽ����̹����� ��Ÿ����

			@Override
			public void mouseExited(MouseEvent e) {

				img.setVisible(false);
				duoButton.setVisible(true);

				soloButton.setIcon(soloButtonBasicImage);
				soloButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}// �ַ� ��� ��ư���� ���� ���� �������̹����� �ٽ� ��Ÿ���� ���Ӽ����̹����� �������

			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic = new Music("�ݼӹ�ư.mp3", false);
				ButtonEnteredMusic.start();

				new JTest();
				dispose(); // ���� ����������

				// ��ư�̺�Ʈ �ַ� ��ư�� ������ JTest��� �������� ����Ǹ鼭 MainGameâ ȭ���� �������
			}
		});

		duoButton.setBounds(780, 150, 500, 450); // ������

		duoButton.setBorderPainted(false);
		duoButton.setContentAreaFilled(false);
		duoButton.setFocusPainted(false);
		duoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				duoButton.setIcon(duoButtonEnteredImage);
				duoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("�ݼӹ�ư.mp3", false);
				ButtonEnteredMusic.start();
				img2.setVisible(true);
				soloButton.setVisible(false);
			}// �ַ� ��� ��ư �̹����� ������ �������̹����� ������� ���Ӽ����̹����� ��Ÿ����

			@Override
			public void mouseExited(MouseEvent e) {
				duoButton.setIcon(duoButtonBasicImage);
				duoButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				img2.setVisible(false);
				soloButton.setVisible(true);
			}// �ַ� ��� ��ư���� ���� ���� �������̹����� �ٽ� ��Ÿ���� ���Ӽ����̹����� �������

			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic = new Music("�ݼӹ�ư.mp3", false);
				ButtonEnteredMusic.start();

				new LookCode();
				dispose(); // ���� ����������

				// ��ư�̺�Ʈ
			}
			// ��ư�̺�Ʈ ��� ��ư �̹����� ������ LookCode��� �������� ����Ǹ鼭 MainGameâ ȭ���� �������
		});

		menuBar.setBounds(0, 0, 1280, 30);// ��ܹ��� ��ġ�� ũ������
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});// ��ܹ� ��ܹٸ� ������ ���콺��ġ��� x��ǥ y��ǥ�� �̵��Ҽ� �ִ�.

	}

	@Override
	public void paint(Graphics g) {// �׸��� �Լ�
		super.paint(g);

		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);

	}

	public void paint2(Graphics g) {// �׸��� �Լ�

		g.drawString(text.getText(), 30, 30);

	}

	public void screenDraw(Graphics g) {
		g.drawImage(Background, 0, 0, null);
		paintComponents(g);
		this.repaint();

	}

}