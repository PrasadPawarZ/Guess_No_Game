package GuessNo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MainGuesserGame extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4;
    JButton b1, b2;
    JTextField t1;
    JRadioButton r1, r2;
    ButtonGroup bg;
    DefaultComboBoxModel<String> difficult, noOfGuess;
    JComboBox<String> difficulty, noOfGuesses;

    private String hint;
    private boolean hints;
    private int currentNoToGuess, guessleft, points;
    private int[] highScores;
    private int currentLevel;// will store how many choices are selected + difficulty
    private static final String FILE_NAME = "D:\\GitHub\\REpos\\OIBSIP\\GuessNo\\data.txt";

    MainGuesserGame() {
        setVisible(true);
        setSize(1280, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Guess the number Game");
        getContentPane().setBackground(Color.gray);

        highScores = new int[18];
        currentLevel = 3;
        hint = "";
        currentNoToGuess = -1;
        hints = true;
        points = 10000;

        l1 = new JLabel();
        l2 = new JLabel();
        l3 = new JLabel();
        l4 = new JLabel();
        b1 = new JButton();
        b2 = new JButton();
        t1 = new JTextField();
        r1 = new JRadioButton("YES");
        r2 = new JRadioButton("NO");
        bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        String[] temp = { "Easy(0-99)", "Medium(0-999)", "Hard(0-9999)" };
        difficult = new DefaultComboBoxModel<>(temp);

        String[] temp2 = { "5", "10", "15", "20", "30", "40" };
        noOfGuess = new DefaultComboBoxModel<>(temp2);

        difficulty = new JComboBox<>(difficult);
        difficulty.setSelectedIndex(0);
        noOfGuesses = new JComboBox<>(noOfGuess);
        noOfGuesses.setSelectedIndex(2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        r1.setBackground(null);
        r2.setBackground(null);

        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(b1);
        add(b2);
        add(difficulty);
        add(noOfGuesses);
        add(r1);
        add(r2);
        add(t1);

        getHighScoreFromFile();
        getCurrentLevelFromFile();
        Homepage();
    }

    private void getCurrentLevelFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            line = reader.readLine();
            hints = Boolean.parseBoolean(line);
            line = reader.readLine();
            currentLevel = Integer.parseInt(line);
            switch (currentLevel) {
                case 1:
                    noOfGuesses.setSelectedIndex(0);
                    difficulty.setSelectedIndex(0);
                    break;
                case 2:
                    noOfGuesses.setSelectedIndex(1);
                    difficulty.setSelectedIndex(0);
                    break;
                case 3:
                    noOfGuesses.setSelectedIndex(2);
                    difficulty.setSelectedIndex(0);
                    break;
                case 4:
                    noOfGuesses.setSelectedIndex(3);
                    difficulty.setSelectedIndex(0);
                    break;
                case 5:
                    noOfGuesses.setSelectedIndex(4);
                    difficulty.setSelectedIndex(0);
                    break;
                case 6:
                    noOfGuesses.setSelectedIndex(5);
                    difficulty.setSelectedIndex(0);
                    break;
                case 7:
                    noOfGuesses.setSelectedIndex(0);
                    difficulty.setSelectedIndex(1);
                    break;
                case 8:
                    noOfGuesses.setSelectedIndex(1);
                    difficulty.setSelectedIndex(1);
                    break;
                case 9:
                    noOfGuesses.setSelectedIndex(2);
                    difficulty.setSelectedIndex(1);
                    break;
                case 10:
                    noOfGuesses.setSelectedIndex(3);
                    difficulty.setSelectedIndex(1);
                    break;
                case 11:
                    noOfGuesses.setSelectedIndex(4);
                    difficulty.setSelectedIndex(1);
                    break;
                case 12:
                    noOfGuesses.setSelectedIndex(5);
                    difficulty.setSelectedIndex(1);
                    break;
                case 13:
                    noOfGuesses.setSelectedIndex(0);
                    difficulty.setSelectedIndex(2);
                    break;
                case 14:
                    noOfGuesses.setSelectedIndex(1);
                    difficulty.setSelectedIndex(2);
                    break;
                case 15:
                    noOfGuesses.setSelectedIndex(2);
                    difficulty.setSelectedIndex(2);
                    break;
                case 16:
                    noOfGuesses.setSelectedIndex(3);
                    difficulty.setSelectedIndex(2);
                    break;
                case 17:
                    noOfGuesses.setSelectedIndex(4);
                    difficulty.setSelectedIndex(2);
                    break;
                case 18:
                    noOfGuesses.setSelectedIndex(5);
                    difficulty.setSelectedIndex(2);
                    break;
                default:
                    break;
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void getHighScoreFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            line = reader.readLine();
            line = reader.readLine();
            int level = 1;
            while ((line = reader.readLine()) != null && level <= highScores.length) {
                int score = Integer.parseInt(line);
                highScores[level - 1] = score;
                level++;
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // also used to store high score
    private void setCurrentLevelInFile(int no) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            if (r1.isSelected()) {
                writer.write("true\n");
            } else {
                writer.write("false\n");
            }
            writer.write(no + "\n");
            for (int score : highScores) {
                writer.write(Integer.toString(score));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setInvisible() {
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        l4.setVisible(false);
        b1.setVisible(false);
        b2.setVisible(false);
        r1.setVisible(false);
        r2.setVisible(false);
        t1.setVisible(false);
        noOfGuesses.setVisible(false);
        difficulty.setVisible(false);
        t1.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        switch (value) {
            case "Start":
                setInvisible();
                gameScreen();
                break;
            case "Restart":
                currentNoToGuess = -1;
                hint = "";
                points = 10000;
                l1.setBounds(270, 100, 800, 200);
                l1.setText("Guess the Number");
                l1.setForeground(Color.black);
                l1.setFont(new Font("Comic Sans MS", 1, 80));
                setInvisible();
                gameScreen();
                break;
            case "Guess":
                guessleft -= 1;
                if (guessleft == 0) {
                    setInvisible();
                    defeatScreen();
                    break;
                }
                if (Integer.parseInt(t1.getText().toString()) == currentNoToGuess) {
                    setInvisible();
                    victoryScreen();
                    break;
                }
                if (Integer.parseInt(t1.getText().toString()) < currentNoToGuess) {
                    if (hints) {
                        hint = "Guess Higher Number";
                    }
                }
                if (Integer.parseInt(t1.getText().toString()) > currentNoToGuess) {
                    if (hints) {
                        hint = "Guess Lower Number";
                    }
                }
                points -= 500;
                t1.setText("");
                gameScreen();
                break;
            case "< Back":
                currentLevel = -1;
                setInvisible();
                Homepage();
                break;
            case "Save":
                int temp1 = difficulty.getSelectedIndex() + 1;
                int temp2 = noOfGuesses.getSelectedIndex() + 1;
                switch (temp1) {
                    case 1:
                        temp1 = 0;
                        break;
                    case 2:
                        temp1 = 6;
                        break;
                    case 3:
                        temp1 = 12;
                        break;
                }
                setCurrentLevelInFile(temp1 + temp2);
                setInvisible();
                Homepage();
                break;
            case "Setting":
                setInvisible();
                settingScreen();
                break;
        }
    }

    void Homepage() {
        currentNoToGuess = -1;
        hint = "";
        points = 10000;
        guessleft = Integer.parseInt((String) noOfGuesses.getSelectedItem());
        l1.setVisible(true);
        b1.setVisible(true);
        b2.setVisible(true);

        b1.setFont(new Font("", 1, 20));
        b2.setFont(new Font("", 1, 20));

        l1.setBounds(270, 100, 800, 200);
        l1.setText("Guess the Number");
        l1.setForeground(Color.black);
        l1.setFont(new Font("Cocmic Sans MS", 1, 80));

        b1.setBounds(550, 300, 150, 60);
        b2.setBounds(550, 400, 150, 60);
        b1.setText("Start");
        b2.setText("Setting");
    }

    private void victoryScreen() {
        l1.setVisible(true);
        l2.setVisible(true);
        b1.setVisible(true);
        b1.setText("Restart");
        b2.setVisible(true);
        l1.setForeground(Color.BLUE);
        l1.setText("YOU WIN !!!");
        l1.setBounds(400, 100, 800, 200);
        l2.setText("");
        if (points > highScores[currentLevel - 1]) {
            l2.setText("New High Score!!!");
            l2.setBounds(500, 400, 300, 60);
            highScores[currentLevel - 1] = points;
            setCurrentLevelInFile(currentLevel);
        }
        b1.setBounds(550, 300, 150, 60);
    }

    private void defeatScreen() {
        l1.setVisible(true);
        b1.setVisible(true);
        b1.setText("Restart");
        b2.setVisible(true);
        l1.setForeground(Color.RED);
        l1.setText("YOU LOST !!!");
        b1.setBounds(550, 300, 150, 60);
        l1.setBounds(400, 100, 800, 200);
    }

    private void settingScreen() {

        getCurrentLevelFromFile();
        noOfGuesses.setVisible(true);
        difficulty.setVisible(true);
        l2.setVisible(true);
        l3.setVisible(true);
        l4.setVisible(true);
        r1.setVisible(true);
        r2.setVisible(true);
        b1.setVisible(true);
        b2.setVisible(true);

        l2.setFont(new Font("", 1, 20));
        l3.setFont(new Font("", 1, 20));
        l4.setFont(new Font("", 1, 20));
        r1.setFont(new Font("", 1, 20));
        r2.setFont(new Font("", 1, 20));
        noOfGuesses.setFont(new Font("", 1, 20));
        difficulty.setFont(new Font("", 1, 20));

        l2.setForeground(Color.BLACK);
        l3.setForeground(Color.BLACK);
        l4.setForeground(Color.BLACK);
        r1.setForeground(Color.BLACK);
        r2.setForeground(Color.BLACK);

        l2.setText("Difficulty : ");
        l3.setText("Number of Guess : ");
        l4.setText("Hints : ");
        b1.setText("Save");
        b2.setText("< Back");

        if (hints) {
            r1.setSelected(true);
        } else {
            r2.setSelected(true);
        }

        l2.setBounds(350, 200, 200, 60);
        l3.setBounds(350, 300, 200, 60);
        l4.setBounds(350, 400, 200, 60);

        difficulty.setBounds(700, 200, 200, 60);
        noOfGuesses.setBounds(700, 300, 200, 60);

        r1.setBounds(700, 400, 100, 60);
        r2.setBounds(850, 400, 100, 60);

        b1.setBounds(550, 500, 200, 60);
        b2.setBounds(100, 100, 200, 60);
    }

    private void gameScreen() {
        getCurrentLevelFromFile();
        // random no
        if (currentNoToGuess == -1) {
            Random random = new Random();
            if (currentLevel < 7) {
                currentNoToGuess = random.nextInt(100);
            } else if (currentLevel < 13) {
                currentNoToGuess = random.nextInt(1000);
            } else {
                currentNoToGuess = random.nextInt(10000);
            }
        }

        l1.setVisible(true);
        l2.setVisible(true);
        l3.setVisible(true);
        l4.setVisible(true);
        t1.setVisible(true);
        b1.setVisible(true);
        b2.setVisible(true);

        l2.setFont(new Font("", 1, 28));
        l2.setText("HIGH SCORE [" + difficulty.getSelectedItem() + "/" + noOfGuesses.getSelectedItem() + "] : " + highScores[currentLevel - 1]);
        l3.setForeground(Color.blue);
        l3.setText(hint);
        l4.setFont(new Font("", 1, 20));
        l4.setText("Points : " + points + " Guessleft : " + guessleft);
        b1.setText("Guess");
        b2.setText("< Back");
        t1.setFont(new Font("", 1, 20));

        l2.setBounds(400, 500, 500, 60);
        l3.setBounds(400, 360, 500, 60);
        l4.setBounds(400, 420, 500, 60);
        t1.setBounds(400, 300, 250, 60);
        b1.setBounds(700, 300, 150, 60);
        b2.setBounds(100, 50, 150, 60);
    }

    public static void main(String[] args) {
        new MainGuesserGame();
    }
}
