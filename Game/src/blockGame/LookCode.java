package blockGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

import java.util.*;
import java.util.Timer;
import java.io.*;
import java.net.*;
import java.sql.ClientInfoStatus;

public class LookCode extends JFrame {

	TetrisPanel TP = new TetrisPanel();
	// TetrisPanel2 TP2 = new TetrisPanel2();
	JLabel scoreInt = new JLabel();
	JLabel scoreString = new JLabel();
	TetrisThread tThread;
	Client tclient = new Client();
	private Image backgroundImage;
	JOptionPane end = new JOptionPane();
	static int blocksize = 20;
	Timer timer = new Timer();

	int End = 0;
	int random = 0, random2 = (int) (Math.random() * 7);

	int score = 0;

	int wid = 100;
	int hgt = 0;
	int rotation = 0;

	boolean limit = false;

	// ��ϵ��� ��ǥ ����
	int curX[] = new int[4], curY[] = new int[4];

	int blocks[][][][] = { {
			// ��
			// ����
			{ { 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
			{ { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
			{ { 1, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } },
			{ { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } } },

			{

					// ��
					// ����
					{ { 0, 0, 0, 0 }, { 0, 0, 1, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 1, 0, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } } },

			{
					// ���
					// ���
					{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } } },

			{
					// �����
					{ { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } } },

			{
					// ��
					// ����
					{ { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } } },

			{
					// ���
					// ���
					{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } } },

			{
					// ���
					// ���
					{ { 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } } } };

	public static int[][] gameboardB = { { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },

			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	LookCode() {
		setTitle("��Ʈ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setSize(1280, 740);
		setLocationRelativeTo(null);
		setVisible(true);

		tThread = new TetrisThread();

		// �г� ������
		TP.setSize(1280, 740);
		TP.setBackground(Color.CYAN);
//      TP2.setSize(640, 720);
//      TP2.setBackground(Color.green);
		add(TP);
//      add(TP2);
//      TP2.setLocation(640, 0);

		scoreInt.setFont(new Font("arial", Font.PLAIN, 30));
		scoreString.setFont(new Font("arial", Font.PLAIN, 30));
		scoreInt.setForeground(Color.white);
		scoreString.setForeground(Color.white);
		// Ű �Է�
		TP.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				int keyCode = e.getKeyCode();

				if (keyCode == KeyEvent.VK_UP) {
					TP.moveUp();
				}

				if (keyCode == KeyEvent.VK_DOWN) {
					TP.moveDown();
				}

				if (keyCode == KeyEvent.VK_LEFT) {
					TP.moveLeft();
				}

				if (keyCode == KeyEvent.VK_RIGHT) {
					TP.moveRight();
				}

			}
		});

		// �г��� ������ �� ���, Ű �Է��� ���� �޵��� ����
		TP.requestFocus(true);

		// ������ ����
		tThread.start();

		// Ŭ���̾�Ʈ ����
		tclient.run();
		// tclient.paintComponent(null);

	}

	class TetrisPanel extends JPanel {
		ImageIcon win = new ImageIcon("win.jpg");
		ImageIcon defeat = new ImageIcon("defeat.jpg");

		public void paintComponent(Graphics g) {

			int cnt = 0, cnt2 = 0;
			TP.requestFocus(true);
			super.paintComponent(g);

			scoreInt.setLocation(540, 420);
			scoreString.setLocation(480, 250);

			TP.add(scoreInt);
			// TP.add(scoreString);

			scoreInt.setText(Integer.toString(score * 100));

			scoreString.setText("SCORE");

			g.setColor(Color.ORANGE); // ���� �������� ��,�̸����� �� ����

			try {
				backgroundImage = ImageIO.read(new File("duo2.png"));
				g.drawImage(backgroundImage, 0, 0, null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// ���� ���� ���� ���
			blockLookAhead(g);

			// ���� õ�忡 ������ ���� ����
			gameOverCheck();

			// �� ���� ��� ������� ä���� ��� ��ϵ� ����(ä���������� ��� ��� ����������)
			removeBlock(cnt, cnt2, g);

			// ����� ���� �����ϸ� ���->������ ��ȯ(�������� ��� �ʱ�ȭ)
			blockToWall();

			// ���� ����
			makeBlock(g);

			// �׵θ� ����
			makeBorder(g);

			if (End == 1) {
				random2 = (int) (Math.random() * 7);
				End = 0;
			}
		}

		// ���� ���� ���� ���
		public void blockLookAhead(Graphics g) {
			for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					if (blocks[random2][0][a][b] == 1) {
						g.fill3DRect(b * blocksize + 520, a * blocksize + 140, blocksize, blocksize, true);
					}
				}
			}
		}

		// ���� õ�忡 ������ ���� ����
		public void gameOverCheck() {
			for (int x = 1; x < 12; x++) {
				if (gameboardB[2][x] == 1) {
					limit = true;
					gameOver();
				} else if (Client.arrayB[2][x] == 1) {
					limit = true;
					gamewin();
				}
			}

		}

		public void gameOver() {
			// end.showMessageDialog(null, "�й� ,��������");
			end.showMessageDialog(null, " ", "����� �й� ", 0, defeat);
			System.exit(0);
		}

		public void gamewin() {
			// end.showMessageDialog(null, "�¸� ,��������");
			end.showMessageDialog(null, " ", "����� �¸� ", 0, win);
			System.exit(0);

		}

		// �� ���� ��� ������� ä���� ��� ��ϵ� ����(ä���������� ��� ��� ����������)
		public void removeBlock(int cnt, int cnt2, Graphics g) {
			for (int y = 0; y < 29; y++) {
				for (int x = 1; x < 20; x++) {
					if (gameboardB[y][x] == 1) {
						cnt2++;
					}
				}
				if (cnt2 == 19) {
					for (int i = y; i > 1; i--)
						for (int j = 1; j < 21; j++) {
							gameboardB[i][j] = 0;
							gameboardB[i][j] = gameboardB[i - 1][j];
						}
					score++;

					// ����� ���� ���ϴ� �� ���� ����
					// ���� ��� ���� �� �� ���ؾ� ��

				} else {
					blockDown(cnt, g); // �� ���� ��� ������� ä������ ���� ���� ����� ���������� ��
				}
				cnt2 = 0;
			}
		}

		// ���� �� ���
		public void makeBlock(Graphics g) {
			g.setColor(Color.GRAY);
			for (int y = 0; y < 29; y++) {
				for (int x = 1; x < 20; x++) {
					if (gameboardB[y][x] == 1) {
						Client.array = gameboardB;
						g.fill3DRect(x * blocksize + 20, y * blocksize + 60, blocksize, blocksize, true);

					}
				}
			}

			g.setColor(Color.GRAY);
			for (int y = 0; y < 29; y++) {
				for (int x = 1; x < 20; x++) {
					if (Client.arrayB[y][x] == 1) {
						g.fill3DRect(640 + (x * blocksize + 20), y * blocksize + 60, blocksize, blocksize, true);
					}
				}
			}
		}

		// �������� ���
		public void blockDown(int cnt, Graphics g) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					if (blocks[random][rotation][j][k] == 1) {
						curX[cnt] = ((k * blocksize) + wid) / blocksize;
						curY[cnt] = ((j * blocksize) + hgt) / blocksize;// curX,Y[0][1][2][3]�� ��ǥ 4�� ����

						// ��ġ ����

						g.fill3DRect(curX[cnt] * blocksize + 20, curY[cnt] * blocksize + 60, blocksize, blocksize,
								true);

						cnt++;
					}
				}
			}
		}

		// ����� ���� �����ϸ� ���->������ ��ȯ(�������� ��� �ʱ�ȭ)
		// �������� ����� ���� �Ǵ��� �˻�
		// ���� �Ǹ� wid=120, hgt=0 ���� ��� �ʱ�ȭ, rotation�� �ʱ�ȭ
		public void blockToWall() {
			try {
				for (int z = 0; z < 4; z++)
					if (gameboardB[curY[z] + 1][curX[z]] == 1)
						for (int j = 0; j < 4; j++) {

							gameboardB[curY[j]][curX[j]] = 1;
							wid = 100;
							hgt = 0;
							End = 1;
							rotation = 0;
							random = random2;
						}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Error");

				for (int i = 0; i < 4; i++) {
					System.out.print("(" + curY[i] + "," + curX[i] + ")");
				}

				System.out.println();
			}

		}

		// ���� ���� �浹�ϸ� �������̵���
		public int collision_LEFT() {
			for (int i = 0; i < 4; i++) {
				if (gameboardB[curY[i]][curX[i] - 1] == 1) // �浹�� 1 ��ȯ
					return 1;
			}
			return 0; // �浹���� ������ 0 ��ȯ
		}

		// ������ ���� �浹�ϸ� �� �����̵���
		public int collision_RIGHT() {

			for (int i = 0; i < 4; i++) {
				if (gameboardB[curY[i]][curX[i] + 1] == 1) // �浹�� 1��ȯ
					return 1;
			}
			return 0; // �浹���� ������ 0��ȯ
		}

		// curX,Y�� ���� ȸ�� ������ ������ǥ�� ��� ����صΰ�, ���� �������̳� ���� X��ǥ1,2ĭ �ȿ� ���� ������ �׸�ŭ ������ Ȥ��
		// �������� �о ��ġ
		public void rotationCheck() {
			// curX,Y�� ���� ȸ�� ������ ������ǥ�� ��� ����صΰ�, �ؿ� �������� �� ������ǥ�� ���� ���� ����� �Ǵ�
			int cnt2 = 0;
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					int rotation2 = (rotation % 4) + 1;
					if (rotation2 == 4)
						rotation2 = 0;
					if (blocks[random][rotation2][j][k] == 1) {
						curX[cnt2] = ((k * blocksize) + wid) / blocksize;
						curY[cnt2] = ((j * blocksize) + hgt) / blocksize;
						cnt2++;
					}
				}
			}

			// curX,Y�� ����� ��ǥ�� �̿�
			int chk = 0;
			int blank = 0;
			int error = 0;

			// ���� ��
			if (gameboardB[curY[0]][curX[0]] == 1 || (random == 6 && gameboardB[curY[2]][curX[2]] == 1)
					|| (random == 1 && gameboardB[curY[1]][curX[1]] == 1)) {
				chk = 1; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� chk=1�� ǥ����
				error++;
				System.out.println("chk1");

				if (random == 3) { // ���ڸ����� ��� ȸ���� ������ �ִ� ������ ������ ȸ������
					for (int i = 1; i < 5; i++) {
						if (gameboardB[curY[0]][curX[0] + i] == 0) {
							blank++;
						}
					}

					if (blank < 4) {
						chk = 4;
					}

					System.out.println(blank);
				} else { // �� ���� ��� ȸ���� ������ ���� ������ ������ ȸ�� ����
					for (int i = 1; i < 4; i++)
						if (gameboardB[curY[0]][curX[0] + i] == 0)
							blank++;
					if (blank < 3)
						chk = 4;
					System.out.println("blank2");
					System.out.println(blank);
				}

			}

			// ������ ��
			else if (gameboardB[curY[2]][curX[2]] == 1) {
				error++;
				chk = 2; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� chk=2�� ǥ����
				System.out.println("chk2");

				for (int i = 1; i < 5; i++)
					if (gameboardB[curY[2]][curX[2] - i] == 0)
						blank++;
				if (blank < 4)
					chk = 4;
				System.out.println("blank2");
				System.out.println(blank);

			} else if (gameboardB[curY[3]][curX[3]] == 1) {
				error++;
				chk = 3; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� chk=3�� ǥ����
				System.out.println("chk3");
				for (int i = 0; i < 5; i++)
					if (gameboardB[curY[3]][curX[3] - i] == 0)
						blank++;
				if (blank < 4)
					chk = 4;
				System.out.println(blank);

			}

			if (chk == 1) {
				// chk = 1(���� ȸ���� ������ ��ġ�� ���� �ߺ��Ǹ�)�� wid(����)�� 30�̵�
				wid += blocksize;
				rotation++;
				rotation = rotation % 4;
			} else if (chk == 2) {
				wid -= blocksize * 2;
				rotation++;
				rotation = rotation % 4;
			} else if (chk == 3) {
				wid -= blocksize;
				rotation++;
				rotation = rotation % 4;
			} else if (chk == 4) {
				System.out.println("ban");
			} else {
				rotation++;
				rotation = rotation % 4;
			}

		}

		// board �׵θ�
		public void makeBorder(Graphics g) {
			g.setColor(Color.GRAY);

			// g.draw3DRect(30, 100, 5, 555,true ); // ���ʱ��
			for (int i = 40; i < 425; i = i + 20) {
				g.drawRect(i, 100, 0, 540);

			} // ���μ�

			for (int i = 100; i < 660; i = i + 20) {
				g.drawRect(40, i, 380, 0);

			} // ���μ�

			for (int i = 660; i < 1080; i = i + 20) {
				g.drawRect(i, 100, 0, 540);

			} // ���μ�

			for (int i = 100; i < 660; i = i + 20) {
				g.drawRect(680, i, 380, 0);

			} // ���μ�
				// g.draw3DRect(425, 100, 5, 555, true); // �����ʱ��

			// (x, y, ����, ����)
			// g.draw3DRect(30, 650, 400, 5, true); // �ٴ�

			// g.draw3DRect(30, 100, 400, 5, true); // õ��

			g.setColor(Color.blue);

			// g.draw3DRect(670, 100, 5, 555, true); // ���ʱ��
			// g.draw3DRect(1065, 100, 5, 555, true); // �����ʱ��

			// (x, y, ����, ����)
			// g.draw3DRect(670, 650, 400, 5, true); // �ٴ�
			// g.draw3DRect(670, 100, 400, 5, true); // õ��
		}

		void down() {
			hgt += blocksize;
			TP.repaint();
		}

		void moveUp() {
			rotationCheck();
			if (limit == false) {
				repaint();
			}
		}

		void moveDown() {
			if (limit == false) {
				hgt += blocksize;
				TP.repaint();
			}
		}

		void moveLeft() {
			int sel = collision_LEFT();

			// sel�� 1�̸� �浹, 0�̸� �浹X
			if (sel == 0 && limit == false) {
				wid -= blocksize;
				TP.repaint();
			}
		}

		void moveRight() {
			int sel = collision_RIGHT();

			// sel�� 1�̸� �浹, 0�̸� �浹X
			if (sel == 0 && limit == false) {
				wid += blocksize;
				TP.repaint();
			}
		}
	}

	class TetrisPanel2 extends JPanel {

		JTextField text;

		public void paintComponent(Graphics g) {
			// super.paintComponent(g);
			// g.setColor(Color.blue);
			makeBorder2(g);
			makeBlock2(g);

		}

		public void makeBorder2(Graphics g) {
			g.setColor(Color.blue);

			g.draw3DRect(30, 100, 5, 555, true); // ���ʱ��
			g.draw3DRect(425, 100, 5, 555, true); // �����ʱ��

			// (x, y, ����, ����)
			g.draw3DRect(30, 650, 400, 5, true); // �ٴ�
			g.draw3DRect(30, 100, 400, 5, true); // õ��

		}

		public void makeBlock2(Graphics g) {
			g.setColor(Color.GRAY);
			for (int y = 0; y < 29; y++) {
				for (int x = 1; x < 20; x++) {
					if (Client.arrayB[y][x] == 1) {
						g.fill3DRect((x * blocksize + 20), y * blocksize + 60, blocksize, blocksize, true);
					}
				}
			}
		}

	}

	class TetrisThread extends Thread {
		int speed = 500;
		TetrisPanel TP = new TetrisPanel();
		// TetrisPanel2 TP2 = new TetrisPanel2();

		public void run() {

			TimerTask task1 = new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					speed = 400;

				}

			};

			TimerTask task2 = new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					speed = 300;
				}

			};
			TimerTask task3 = new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					speed = 220;
				}

			};
			TimerTask task4 = new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					speed = 140;
				}

			};
			TimerTask task5 = new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					speed = 100;
				}

			};

			if (Client.portnum != null) {
				timer.schedule(task1, 10000);
				timer.schedule(task2, 20000);
				timer.schedule(task3, 30000);
				timer.schedule(task4, 40000);
				timer.schedule(task5, 50000);
			}
			while (true) {
				try {
					while (Client.portnum == null) {
						tThread.interrupt();

						if (Client.portnum != null) {
							tThread.run();
						}
					}

					sleep(speed);
					if (limit == false) // limit�� false�� ��쿡�� �۵�. true�� �Ǹ� ��Ʈ���� �۵�����
						TP.down();
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	////
	static class Client extends Thread {

		String IP = "";
		Socket s = null;

		static String portnum;
		ObjectOutputStream os = null;// ��

		ObjectInputStream is = null;

		// static int[][] arrayB = ServerLookCode.gameboardA;

		static int[][] array = gameboardB;
		static int[][] arrayB = new int[29][20];

		int port;

		@Override
		public void run() {

			try {
				IP = JOptionPane.showInputDialog("ip�� �Է��ϼ���");
				portnum = JOptionPane.showInputDialog("��Ʈ��ȣ�� �Է��ϼ���");

				port = Integer.parseInt(portnum);

				s = new Socket(IP, port);

				while (true) {
					// System.out.println(Arrays.deepToString(array));
					os = new ObjectOutputStream(s.getOutputStream());
					os.writeObject(array); // ��-b - Ŭ��

					is = new ObjectInputStream(s.getInputStream());
					arrayB = (int[][]) is.readObject(); // �� -a -����

				}

			} catch (Exception e) {

			}
		}
	}

	////

	public static void main(String[] args) {
		new LookCode();

	}
}

/*
 * 
 * �������̶�?
 * 
 * -��ſ� �ʿ��� ������ ��� �ִ� ��ü
 * 
 * ����Ʈ��ȣ ��? (����ϴ� ���α׷��� �����ϱ� ���� �ĺ���ȣ)
 * 
 * ������ ���� ������ -������ ���α׷��� �ۼ��� �� ���. -�ٸ� ���α׷��� �ߺ����� �ʵ��� ��Ʈ ��ȣ�� ����
 * 
 * ��Ŭ���̾�Ʈ ���� ������ -Socket Ŭ������ �̿��Ͽ� ���� -�����Ϸ��� ����� ip �ּ� �Է�(���� ��Ʈ��ũ �ȿ���) -���� ������
 * ��Ʈ ��ȣ�� ������ ��ȣ ��� -��� ���� ��Ʈ ��ȣ�� �ߺ� ��� �Ұ� -������ Ư�� �������� �θ� �̿�Ǵ� ��Ʈ ��ȣ�� �����������
 * 
 * ����Ʈ���� �������� ������ �迭. ������ſ��� �������迭�� ������ �Ľ����ε� �����ѵ� ��Ʈ������ �� �ӵ��� ��������
 * �������迭->���ڿ�->�Ľ�->�������迭 ��ȯ�� �ӵ��� ���� �׷��� �̿��Ѱ� ->
 * 
 * ���ڹ� ��ü ����ȭ/������ȭ ���
 * 
 * ObjectInputStream & ObjectOutputStream
 * 
 * ������ȭ /������ȭ��? ��ü�� �����ͽ�Ʈ���� �������� �������� ���������� ����� �� ->��ü�� �������� ����Ʈ�� ǥ���� �����Ͱ���
 * 
 * ����ȭ(��Ʈ���� ��ü�� ž��)���� ObjectInputStream�� ����ϰ� ������ȭ(��Ʈ�����κ��� ��ü�� ����)����
 * ObjectOutputStream�� ����Ѵ�.
 * 
 * ObjectInputStream�� ObjectOutputStream�� ���� InputStream / OutputStream�� ����
 * ��ӹ����� �߻�Ŭ�����̴�. �׷��� ��ü�� ������ �� �����(����ȭ/������ȭ)�� ��Ʈ���� �������־�� �Ѵ�.
 * 
 * InputStream �� OutputStream�� ���Ͽ��� ������ �޾ƿ´�.
 * 
 * 
 * 
 * static int[][]arrayA = new int [29][20];(��) static int[][]arrayB = new int
 * [29][20];(Ŭ) //������ Ŭ���̾�Ʈ�� �� ������ 2�����迭 ����
 * 
 * static int [][] array2 = gameboardA;(��) static int[][]array = gameboardB;(Ŭ)
 * //���� �� ������ ����Ŭ����/Ŭ���̾�Ʈ Ŭ������ ����
 * 
 * �������� is = new ObjectInputStream(s.getInputStream()); arrayA = (int[][])
 * is.readObject(); //��-b - Ŭ�� //�� �迭�� inputstream ���� ������ �����͸� ������ȭ �Ͽ� //int��
 * �������迭�� ��ȯ, �� �迭�� ����
 * 
 * os = new ObjectOutputStream(s.getOutputStream()); os.writeObject(array2);
 * 
 * //��Ʈ���� ���� ���� �����͸� ����ȭ �Ͽ� //�������� ����Ʈ �����ͷ� ���Ͽ� ��� ����
 * 
 * ��Ŭ���̾�Ʈ�� os = new ObjectOutputStream(s.getOutputStream());
 * os.writeObject(array); //��-b - Ŭ�� //��Ʈ���� ���� ���� �����͸� ����ȭ �Ͽ� //�������� ����Ʈ �����ͷ�
 * ���Ͽ� ��� ����
 * 
 * is = new ObjectInputStream(s.getInputStream()); arrayB = (int[][])
 * is.readObject(); //�� -a -���� //�� �迭�� inputstream ���� ������ �����͸� ������ȭ �Ͽ� //int��
 * �������迭�� ��ȯ, �� �迭�� ����
 * 
 * �����̵� ���� 1.TimerTaskŬ������ �̿��� �۾� ��ĳ�ٸ��� �̿�. 2.schedule() �޼ҵ�� Ư���� �ð��� ���ϴ� �۾���
 * �����ϰ��� �� �� ����ϴ� �޼ҵ� 3.������ �ð�(ms) �� ������, �۾�(task) �����ϵ��� ���� 4.�ð� ���� ���� �ٸ� ��ġ��
 * �־, �� ��ġ�� �����忡 sleep�޼����� ���ڷ� ��� 5.����ڰ� ip��ȣ�� ��Ʈ �ѹ��� ���� ������ �����忡 interrupt()
 * �޼��带 ����Ͽ� �۾��� �������� �ʵ��� ��
 * 
 */
