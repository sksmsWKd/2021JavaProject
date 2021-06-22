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

public class ServerLookCode extends JFrame {

	TetrisPanel TP = new TetrisPanel();
	// TetrisPanel2 TP2 = new TetrisPanel2();
	JLabel scoreInt = new JLabel();
	JLabel scoreString = new JLabel();
	TetrisThread tThread;
	Server tserver = new Server();
	private Image backgroundImage;
	Timer timer = new Timer();
	JOptionPane end = new JOptionPane();

	static int blocksize = 20;

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

	static int[][] gameboardA = { { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
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

	ServerLookCode() {
		setTitle("��Ʈ���� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setSize(1280, 740);
		setLocationRelativeTo(null);
		setVisible(true);

		tThread = new TetrisThread();

		// �г� ������
		TP.setSize(1280, 740);
		TP.setBackground(Color.gray);
		// TP2.setSize(640, 720);
		// TP2.setBackground(Color.green);
		add(TP);
		// add(TP2);
		// TP2.setLocation(640, 0);

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

		// ��Ž���
		tserver.run();
		// tserver.paintComponent();
	}

	class TetrisPanel extends JPanel {

		ImageIcon win = new ImageIcon("win.jpg");
		ImageIcon defeat = new ImageIcon("defeat.jpg");

		public void paintComponent(Graphics g) {

			int cnt = 0, cnt2 = 0;
			TP.requestFocus(true);
			super.paintComponent(g);

			scoreInt.setLocation(525, 420);
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
						g.fill3DRect(b * blocksize + 500, a * blocksize + 130, blocksize, blocksize, true);
					}
				}
			}
		}

		// ���� õ�忡 ������ ���� ����
		public void gameOverCheck() {
			for (int x = 1; x < 12; x++) {

				if (gameboardA[2][x] == 1) {
					limit = true;
					gameOver();
				} else if (Server.arrayA[2][x] == 1) {
					limit = true;
					gamewin();
				}

			}

		}

		public void gameOver() {
			// end.showMessageDialog(null, "�й� ,��������");
			end.showMessageDialog(null, " ", " ����� �й�", 0, defeat);
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
					if (gameboardA[y][x] == 1) {
						cnt2++;
					}
				}
				if (cnt2 == 19) {
					for (int i = y; i > 1; i--)
						for (int j = 1; j < 21; j++) {
							gameboardA[i][j] = 0;
							gameboardA[i][j] = gameboardA[i - 1][j];
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
					if (gameboardA[y][x] == 1) {
						g.fill3DRect(x * blocksize + 20, y * blocksize + 60, blocksize, blocksize, true);
					}
				}
			}
			g.setColor(Color.blue);
			for (int y = 0; y < 29; y++) {
				for (int x = 1; x < 20; x++) {
					if (Server.arrayA[y][x] == 1) {
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
					if (gameboardA[curY[z] + 1][curX[z]] == 1)
						for (int j = 0; j < 4; j++) {

							gameboardA[curY[j]][curX[j]] = 1;
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
				if (gameboardA[curY[i]][curX[i] - 1] == 1) // �浹�� 1 ��ȯ
					return 1;
			}
			return 0; // �浹���� ������ 0 ��ȯ
		}

		// ������ ���� �浹�ϸ� �� �����̵���
		public int collision_RIGHT() {

			for (int i = 0; i < 4; i++) {
				if (gameboardA[curY[i]][curX[i] + 1] == 1) // �浹�� 1��ȯ
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
			if (gameboardA[curY[0]][curX[0]] == 1 || (random == 6 && gameboardA[curY[2]][curX[2]] == 1)
					|| (random == 1 && gameboardA[curY[1]][curX[1]] == 1)) {
				chk = 1; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� chk=1�� ǥ����
				error++;
				System.out.println("chk1");

				if (random == 3) { // ���ڸ����� ��� ȸ���� ������ �ִ� ������ ������ ȸ������
					for (int i = 1; i < 5; i++) {
						if (gameboardA[curY[0]][curX[0] + i] == 0) {
							blank++;
						}
					}

					if (blank < 4) {
						chk = 4;
					}

					System.out.println(blank);
				} else { // �� ���� ��� ȸ���� ������ ���� ������ ������ ȸ�� ����
					for (int i = 1; i < 4; i++)
						if (gameboardA[curY[0]][curX[0] + i] == 0)
							blank++;
					if (blank < 3)
						chk = 4;
					System.out.println("blank2");
					System.out.println(blank);
				}

			}

			// ������ ��
			else if (gameboardA[curY[2]][curX[2]] == 1) {
				error++;
				chk = 2; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� chk=2�� ǥ����
				System.out.println("chk2");

				for (int i = 1; i < 5; i++)
					if (gameboardA[curY[2]][curX[2] - i] == 0)
						blank++;
				if (blank < 4)
					chk = 4;
				System.out.println("blank2");
				System.out.println(blank);

			} else if (gameboardA[curY[3]][curX[3]] == 1) {
				error++;
				chk = 3; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� chk=3�� ǥ����
				System.out.println("chk3");
				for (int i = 0; i < 5; i++)
					if (gameboardA[curY[3]][curX[3] - i] == 0)
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
			g.setColor(Color.black);
			for (int y = 0; y < 29; y++) {
				for (int x = 1; x < 20; x++) {
					if (Server.arrayA[y][x] == 1) {
						g.fill3DRect(x * blocksize + 20, y * blocksize + 60, blocksize, blocksize, true);
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
			if (Server.port != null) {
				timer.schedule(task1, 10000);
				timer.schedule(task2, 20000);
				timer.schedule(task3, 30000);
				timer.schedule(task4, 40000);
				timer.schedule(task5, 50000);
			}
			while (true) {
				try {
					while (Server.port == null) {
						tThread.interrupt();

						if (Server.port != null) {
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
	static class Server extends Thread {
		ServerSocket ss = null;
		Socket s = null;
		ObjectInputStream is = null; // ��

		ObjectOutputStream os = null;

		static int[][] arrayA = new int[29][20];

		static int[][] array2 = gameboardA;

		static String port;

		static int portint;

		@Override
		public void run() {

			try {

				port = JOptionPane.showInputDialog("��Ʈ��ȣ�� �Է��ϼ���");
				portint = Integer.parseInt(port);

				if (port != null) {

					ss = new ServerSocket(portint);

					s = ss.accept(); // ������ ���� �Ҵ�

					System.out.println("Client�� ������ : " + s.getLocalAddress()); // �������� getLocalAddress ��������

					while (true) {

						is = new ObjectInputStream(s.getInputStream());
						arrayA = (int[][]) is.readObject(); // ��-b - Ŭ��

						os = new ObjectOutputStream(s.getOutputStream());
						os.writeObject(array2);

						// System.out.println(Arrays.deepToString(arrayA));

//
//               os = new ObjectOutputStream(s.getOutputStream());
//               os.writeObject(gameboardA); //�� - a - ����

					}
				}
			} catch (Exception e) {

			}

		}

	}

	////

	public static void main(String[] args) {
		new ServerLookCode();

	}
}