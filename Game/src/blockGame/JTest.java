package blockGame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JTest extends JFrame {

   TetrisPanel TP = new TetrisPanel();
   JLabel scoreInt = new JLabel();
   TetrisThread tThread;
   JLabel userName = new JLabel(MainGame.userName);
   JPanel GameOver = new JPanel();

   private ImageIcon restartButtonImage = new ImageIcon("./src/blockGame/image/reStartBlack.png");
   private ImageIcon restartButtonBasicImage = new ImageIcon("./src/blockGame/image/reStart.png");

   private ImageIcon homeButtonImage = new ImageIcon("./src/blockGame/image/home.png");
   private ImageIcon homeButtonBasicImage = new ImageIcon("./src/blockGame/image/home2.png");

   private JButton home = new JButton(homeButtonImage);

   private JButton RBtn = new JButton(restartButtonBasicImage);

   private Image background = new ImageIcon("./src/blockGame/image/new.png").getImage();
   private Image gameover = new ImageIcon("./src/blockGame/image/gameover.png").getImage();
   static int blocksize = 30;
   static int garoKan = 15;
   static int seroKan = 19;
   static int scoreSum = 0;

   int endCheck = 0;
   int test = 0;

   int randomColor;

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
               { { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } }, },

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

   int[][] gameboard = {

         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

         { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }

   };

   JTest() {
      setTitle("��Ʈ����");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(null);
      setResizable(false);
      setSize(1280, 720);
      setLocationRelativeTo(null);
      setVisible(true);

      TP.add(RBtn);
      TP.add(home);

      tThread = new TetrisThread();

      RBtn.setVisible(false);
      home.setVisible(false);

      // �г� ������
      TP.setSize(1280, 720);
      add(TP);

      // �г� ������ �����ض� ������
      GameOver.setSize(1280, 281);

      TP.add(userName);

      userName.setForeground(Color.white);
      Font font = new Font("�������", Font.BOLD, 40);
      userName.setFont(font);

      scoreInt.setFont(new Font("arial", Font.PLAIN, 40));
      scoreInt.setForeground(Color.white);

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

      RBtn.setBounds(440, 470, 400, 100); // ���۹�ư ��ġ
      RBtn.setBorderPainted(false);
      RBtn.setContentAreaFilled(false);
      RBtn.setFocusPainted(false);
      RBtn.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            RBtn.setIcon(restartButtonImage);
            RBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            Music ButtonEnteredMusic = new Music("�ݼӹ�ư.mp3", false);
            ButtonEnteredMusic.start();// ��ư�� ������� �Ҹ������������� �Ҹ��� ������Ǻ��� �۾Ƽ� �߾ȵ鸱���� �ִ�.

         }

         @Override
         public void mouseExited(MouseEvent e) {

            RBtn.setIcon(restartButtonBasicImage);
            RBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

         }// �ַ� ��� ��ư���� ���� ���� �������̹����� �ٽ� ��Ÿ���� ���Ӽ����̹����� �������

         @Override
         public void mousePressed(MouseEvent e) {
            Music ButtonEnteredMusic = new Music("�ݼӹ�ư.mp3", false);
            ButtonEnteredMusic.start();

            home.setVisible(false);
            limit = false;
            for (int y = 0; y < 19; y++)
               for (int x = 1; x < 16; x++)
                  gameboard[y][x] = 0;
            score = 0;
            test = 0;
            endCheck = 0;
            scoreSum = 0;
            if (endCheck == 0) {
               TP.remove(RBtn);

            }
         }
      });

      home.setBounds(440, 560, 400, 100); // ���۹�ư ��ġ
      home.setBorderPainted(false);
      home.setContentAreaFilled(false);
      home.setFocusPainted(false);
      home.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            home.setIcon(homeButtonBasicImage);
            home.setCursor(new Cursor(Cursor.HAND_CURSOR));
            Music ButtonEnteredMusic = new Music("�ݼӹ�ư.mp3", false);
            ButtonEnteredMusic.start();// ��ư�� ������� �Ҹ������������� �Ҹ��� ������Ǻ��� �۾Ƽ� �߾ȵ鸱���� �ִ�.

         }

         @Override
         public void mouseExited(MouseEvent e) {

            home.setIcon(homeButtonImage);
            home.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

         }// �ַ� ��� ��ư���� ���� ���� �������̹����� �ٽ� ��Ÿ���� ���Ӽ����̹����� �������

         @Override
         public void mousePressed(MouseEvent e) {
            Music ButtonEnteredMusic = new Music("�ݼӹ�ư.mp3", false);
            ButtonEnteredMusic.start();

            new MainGame();
            dispose(); // ���� ����������

         }
      });

      // �г��� ������ �� ���, Ű �Է��� ���� �޵��� ����
      TP.requestFocus(true);

      // ������ ����
      tThread.start();
   }

   class TetrisPanel extends JPanel {

      public void paintComponent(Graphics g) {

         int cnt = 0, cnt2 = 0;
         TP.requestFocus(true);
         userName.setLocation(135, 130); // ��ġ
         super.paintComponent(g);
         
         home.setLocation(150, 500);
         RBtn.setLocation(700, 500);
         
         scoreInt.setLocation(1050, 190);
         TP.add(scoreInt);

         scoreInt.setText(Integer.toString(scoreSum + (score * 20)));
         setLocationRelativeTo(null);// â�� ��� ������

         switch (randomColor) {

         case 0: {
            g.setColor(Color.decode("#FF5675")); // ���� �������� ��,�̸����� �� ����
            break;
         }
         case 1: {
            g.setColor(Color.decode("#3DFF92"));
            break;
         }
         case 2: {
            g.setColor(Color.decode("#FFEB5A"));
            break;
         }
         case 3: {
            g.setColor(Color.decode("#B2FA5C"));
            break;
         }
         case 4: {
            g.setColor(Color.decode("#00D7FF"));
            break;
         }
         case 5: {
            g.setColor(Color.decode("#FFA98F"));
            break;
         }
         case 6: {
            g.setColor(Color.decode("#00FFFF"));
            break;
         }

         }

         g.drawImage(background, 0, 0, null);

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

         if (endCheck == 1) {
            // �г� �̹���

            RBtn.setVisible(true);
            home.setVisible(true);
            TP.add(RBtn);
            TP.add(home);

            g.drawImage(gameover, 0, 200, null);

         }

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
                  g.fill3DRect(b * blocksize + 1050, a * blocksize + 425, blocksize, blocksize, true);
                  randomColor = (int) (Math.random() * 7);
               }
            }
         }
      }

      // ���� õ�忡 ������ ���� ���� �̺κ� ����
      public void gameOverCheck() {
         for (int x = 1; x < 15; x++) {

            if (gameboard[2][x] == 1) {
               limit = true;
               endCheck = 1;
            }
         }

      }

      // �� ���� ��� ������� ä���� ��� ��ϵ� ����(ä���������� ��� ��� ����������)
      public void removeBlock(int cnt, int cnt2, Graphics g) {
         for (int y = 0; y < 19; y++) {
            for (int x = 1; x < 16; x++) {
               if (gameboard[y][x] == 1) {
                  cnt2++;
               }
            }
            if (cnt2 == 15) {
               for (int i = y; i > 1; i--)
                  for (int j = 1; j < 16; j++) {
                     gameboard[i][j] = 0;
                     gameboard[i][j] = gameboard[i - 1][j];
                  }
               scoreSum += 300;

            } else {
               blockDown(cnt, g); // �� ���� ��� ������� ä������ ���� ���� ����� ���������� ��
            }
            cnt2 = 0;
         }
      }

      // ���� �� ���
      public void makeBlock(Graphics g) {

         g.setColor(Color.GRAY);
         for (int y = 0; y < 19; y++) {
            for (int x = 1; x <= 15; x++) {
               if (gameboard[y][x] == 1) {
                  g.fill3DRect((x * blocksize) + 380, y * blocksize + 75, blocksize, blocksize, true);
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

                  g.fill3DRect((curX[cnt] * blocksize) + 380, curY[cnt] * blocksize + 75, blocksize, blocksize,
                        true);

                  cnt++;
               }
            }
         }
      }

      public void addScore() {
         score++;
      }

      // ����� ���� �����ϸ� ���->������ ��ȯ(�������� ��� �ʱ�ȭ)
      // �������� ����� ���� �Ǵ��� �˻�
      // ���� �Ǹ� wid=120, hgt=0 ���� ��� �ʱ�ȭ, rotation�� �ʱ�ȭ
      public void blockToWall() {
         try {
            for (int z = 0; z < 4; z++)
               if (gameboard[curY[z] + 1][curX[z]] == 1)
                  for (int j = 0; j < 4; j++) {

                     gameboard[curY[j]][curX[j]] = 1;
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

         }

      }

      // ���� ���� �浹�ϸ� �������̵���
      public int collision_LEFT() {
         for (int i = 0; i < 4; i++) {
            if (gameboard[curY[i]][curX[i] - 1] == 1) // �浹�� 1 ��ȯ
               return 1;
         }
         return 0; // �浹���� ������ 0 ��ȯ
      }

      // ������ ���� �浹�ϸ� �� �����̵���
      public int collision_RIGHT() {

         for (int i = 0; i < 4; i++) {
            if (gameboard[curY[i]][curX[i] + 1] == 1) // �浹�� 1��ȯ
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
         if (gameboard[curY[0]][curX[0]] == 1 || (random == 6 && gameboard[curY[2]][curX[2]] == 1)
               || (random == 1 && gameboard[curY[1]][curX[1]] == 1)) {
            chk = 1; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� chk=1�� ǥ����
            error++;
            System.out.println("chk1");

            if (random == 3) { // ���ڸ����� ��� ȸ���� ������ �ִ� ������ ������ ȸ������
               for (int i = 1; i < 5; i++) {
                  if (gameboard[curY[0]][curX[0] + i] == 0) {
                     blank++;
                  }
               }

               if (blank < 4) {
                  chk = 4;
               }

               System.out.println(blank);
            } else { // �� ���� ��� ȸ���� ������ ���� ������ ������ ȸ�� ����
               for (int i = 1; i < 4; i++)
                  if (gameboard[curY[0]][curX[0] + i] == 0)
                     blank++;
               if (blank < 3)
                  chk = 4;
               System.out.println("blank2");
               System.out.println(blank);
            }

         }

         // ������ ��
         else if (gameboard[curY[2]][curX[2]] == 1) {
            error++;
            chk = 2; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� chk=2�� ǥ����
            System.out.println("chk2");

            for (int i = 1; i < 5; i++)
               if (gameboard[curY[2]][curX[2] - i] == 0)
                  blank++;
            if (blank < 4)
               chk = 4;
            System.out.println("blank2");
            System.out.println(blank);

         } else if (gameboard[curY[3]][curX[3]] == 1) {
            error++;
            chk = 3; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� chk=3�� ǥ����
            System.out.println("chk3");
            for (int i = 0; i < 5; i++)
               if (gameboard[curY[3]][curX[3] - i] == 0)
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

      // ��ġ ���� �ٽ��ؾ���

      // board �׵θ�
      public void makeBorder(Graphics g) {
         g.setColor(Color.GRAY);
         for (int row = 0; row < seroKan; row++) { // ������ x 30 ���� ���� (��1�� ��)
            g.drawLine(410, blocksize * row + 135, blocksize * 15 + 410, blocksize * row + 135);
         }

         for (int col = 0; col < garoKan + 1; col++) { // ������, y 180 ���� ���� (��6�� ��)
            g.drawLine(col * blocksize + 410, 135, col * blocksize + 410, blocksize * 17 + 135);
         }
      }

      // ���η� �� �� �� ���� 15ĭ
      // ���η� �� �� �� ���� 19 ĭ

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

   class TetrisThread extends Thread {
      // TetrisPanel TP = new TetrisPanel();

      public void run() {
         while (true) {
            try {
               sleep(200);
               if (limit == false) // limit�� false�� ��쿡�� �۵�. true�� �Ǹ� ��Ʈ���� �۵�����
                  TP.down();
            } catch (InterruptedException e) {
               return;
            }
         }
      }
   }

   public static void main(String[] args) {
      new JTest();

   }
}