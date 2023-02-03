import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class GameFrame extends JFrame implements ActionListener {

    int turn=1;
    static Vector<String> currentBoard = new Vector<>(9);

    GameFrame()
    {
         //initialize board

        this.setTitle("Simple TicTacToe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,300);
        this.setMinimumSize(new Dimension(300,300));
        this.setLayout(new GridLayout(3,3));

        this.setBackground(Color.WHITE);
        this.setVisible(true);

        refresh();

    }

    private void refresh()
    {
        for(int i=0; i<9; i++) currentBoard.add("");

        this.getContentPane().removeAll();

        for(int buttonNumber = 0; buttonNumber<9; buttonNumber++)
        {
            JButton newButton = new JButton();
            newButton.addActionListener(this);
            newButton.setActionCommand(String.valueOf(buttonNumber));
            newButton.setText(currentBoard.elementAt(buttonNumber));
            newButton.setFont(new Font("Arial", Font.PLAIN, 18));

            //Add preview effect
            newButton.addMouseListener(new java.awt.event.MouseAdapter() {
                String before = newButton.getText();
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    if(turn%2==0 && before.equals(""))
                    {
                        newButton.setText("O");
                    }
                    else if(turn%2==1 && before.equals(""))
                    {
                        newButton.setText("X");
                    }
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    newButton.setText(before);
                }
            });

            this.add(newButton);
        }
        this.repaint();
        this.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int pressedButtonIndex=Integer.parseInt(e.getActionCommand());

        if(currentBoard.elementAt(pressedButtonIndex).equals("")) //if button is still free
        {
            if(turn%2==0) //if turn is multiply of 2 -> Circles Turn
            {
                currentBoard.remove(pressedButtonIndex);
                currentBoard.add(pressedButtonIndex,"O");
                turn++;
                refresh();

            }
            else
            {
                currentBoard.remove(pressedButtonIndex);
                currentBoard.add(pressedButtonIndex,"X");
                turn++;
                refresh();

            }
        }
        checkWinCondition();
    }

    public void reset()
    {
        currentBoard.removeAllElements();
        turn=1;
        refresh();
    }

    public void checkWinCondition()
    {
        for(int currentCandidate = 0; currentCandidate<currentBoard.size(); currentCandidate++ )
        {
            String fieldValue = currentBoard.elementAt(currentCandidate);

            if(!fieldValue.equals(""))
            {
                //check all 6 directions for 3 in a row
                if(currentCandidate==2 || currentCandidate==5 || currentCandidate==8) //2 W
                {
                    String left1=currentBoard.elementAt(currentCandidate-1);
                    String left2=currentBoard.elementAt(currentCandidate-2);

                    if(fieldValue.equals(left1) && fieldValue.equals(left2))
                    {
                        JOptionPane.showMessageDialog(null, "Winner is " +fieldValue, "Win", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                        return;
                    }
                }

                if(currentCandidate==8) // 2 NW
                {
                    String nw1=currentBoard.elementAt(4);
                    String nw2=currentBoard.elementAt(0);

                    if(fieldValue.equals(nw1) && fieldValue.equals(nw2))
                    {
                        JOptionPane.showMessageDialog(null, "Winner is " +fieldValue, "Win", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                        return;
                    }
                }

                if(currentCandidate==6 || currentCandidate==7 || currentCandidate==8) //2 N
                {
                    String n1=currentBoard.elementAt(currentCandidate-3);
                    String n2=currentBoard.elementAt(currentCandidate-6);

                    if(fieldValue.equals(n1) && fieldValue.equals(n2))
                    {
                        JOptionPane.showMessageDialog(null, "Winner is " +fieldValue, "Win", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                        return;
                    }
                }

                if(currentCandidate==6) //2 NE
                {
                    String ne1=currentBoard.elementAt(4);
                    String ne2=currentBoard.elementAt(2);

                    if(fieldValue.equals(ne1) && fieldValue.equals(ne2))
                    {
                        JOptionPane.showMessageDialog(null, "Winner is " +fieldValue, "Win", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                        return;
                    }
                }

                if(currentCandidate==0 || currentCandidate==3 || currentCandidate==6) //2 E
                {
                    String e1=currentBoard.elementAt(currentCandidate+1);
                    String e2=currentBoard.elementAt(currentCandidate+2);

                    if(fieldValue.equals(e1) && fieldValue.equals(e2))
                    {
                        JOptionPane.showMessageDialog(null, "Winner is " +fieldValue, "Win", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                        return;
                    }
                }

                if(currentCandidate==0) //2 SE
                {
                    String se1=currentBoard.elementAt(4);
                    String se2=currentBoard.elementAt(8);

                    if(fieldValue.equals(se1) && fieldValue.equals(se2))
                    {
                        JOptionPane.showMessageDialog(null, "Winner is " +fieldValue, "Win", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                        return;
                    }
                }

                if(currentCandidate==0 || currentCandidate==1 || currentCandidate==2) //2 S
                {
                    String s1=currentBoard.elementAt(currentCandidate+3);
                    String s2=currentBoard.elementAt(currentCandidate+6);

                    if(fieldValue.equals(s1) && fieldValue.equals(s2))
                    {
                        JOptionPane.showMessageDialog(null, "Winner is " +fieldValue, "Win", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                        return;
                    }
                }

                if(currentCandidate==2) //2 SW
                {
                    String s1=currentBoard.elementAt(4);
                    String s2=currentBoard.elementAt(6);

                    if(fieldValue.equals(s1) && fieldValue.equals(s2))
                    {
                        JOptionPane.showMessageDialog(null, "Winner is " +fieldValue, "Win", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                        return;
                    }
                }
            }

        }
        if(turn==10) //No winner but 9 rounds played
        {
            JOptionPane.showMessageDialog(null, "There is no Winner. Restarting", "Tie", JOptionPane.INFORMATION_MESSAGE);
            reset();
        }
    }
}
