package chess;
import instructions.* ;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;

public class Chess extends JFrame implements Runnable {
    static final int XBORDER = 20;
    static final int YBORDER = 20;
    static final int YTITLE = 30;
    static final int WINDOW_BORDER = 8;
    static final int WINDOW_WIDTH = 2*(WINDOW_BORDER + XBORDER) + 800;
    static final int WINDOW_HEIGHT = YTITLE + WINDOW_BORDER + 2 * YBORDER + 800;
    boolean animateFirstTime = true;
    static int xsize = -1;
    static int ysize = -1;
    Image image;
    Graphics2D g;
    final int numRows = 8;
    final int numColumns = 8;
    final int pNumRows = 1;
    final int pNumColumns = 5;
    int turnCount;
    int currentColumn;
    int currentRow;
    int selectedColumn;
    int selectedRow;
    int hoverColumn;
    int hoverRow;
    int addLoyalty;
    int timeCount;
    int compareTime;
    boolean drawConversionSuccess;
    static boolean drawConversionFail;
    boolean pieceSwitcher;
    boolean IMenu;
    boolean Instrucitons;
    boolean back;
    boolean Menu;
    boolean moveHappend;
    boolean alreadySelected;
    boolean player1;
    boolean gameOver;
    instructions info = new instructions();
    Piece board[][];
    Piece pieceChange[][];
    int tempRow;
    int tempCol;
    enum WinState
    {
        PLAYER1,PLAYER2,NONE
    }
    WinState winState;
    Color tColor =new Color(211,211,211,153);
    Color tColor2 =new Color(211,211,211,225);
    
    sound click = null; 
    
    Image ImgMenu;

    Image IWPawn;
    Image IBPawn;
    Image IWBishop;
    Image IBBishop;
    Image IWRook;
    Image IBRook;
    Image IWKnight; 
    Image IBKnight;
    Image IWKing;
    Image IBKing;
    Image IWQueen;
    Image IBQueen;
    Image IWPriest;
    Image IBPriest;      
    
    boolean playerOnesTurn;
    static Chess frame1;
    public static void main(String[] args) {
        frame1 = new Chess();
        //frame1.setUndecorated(true);
        frame1.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        //frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }

    public Chess() {

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button 
                    int xpos = e.getX() - getX(0);
                    int ypos = e.getY() - getY(0);
                    if (xpos < 0 || ypos < 0 || xpos > getWidth2() || ypos > getHeight2())
                        return;
                    //Calculate the width and height of each board square.
                    int BlockHeight = getHeight2()/numRows;
                    int BlockWidth = getWidth2()/numColumns;
                    currentColumn = xpos/BlockWidth;
                    currentRow = ypos/BlockHeight;
                    if(Menu&&(currentColumn==4&&currentRow==4||currentColumn==3&&currentRow==3||currentColumn==3&&currentRow==4||currentColumn==4&&currentRow==3))
                        reset();
                    
                    if(IMenu&&Instrucitons&&(currentColumn==2&&currentRow==3||currentColumn==3&&currentRow==3||currentColumn==4&&currentRow==3||currentColumn==5&&currentRow==3))
                    {
                        IMenu=false;
                    }
                    if(IMenu&&(currentColumn==2&&currentRow==5||currentColumn==3&&currentRow==5||currentColumn==4&&currentRow==5||currentColumn==5&&currentRow==5))
                    {
                        Instrucitons=false;
                       
                    }
                    if(!Instrucitons&&(currentColumn==3&&currentRow==7||currentColumn==4&&currentRow==7))
                    {
                       reset();         
                    }
                    if(!IMenu)
                    {
                    pieceSwitcher = Piece.pieceSwitcher(board,currentRow,currentColumn,tempRow,tempCol,pieceSwitcher);
                    if(player1)
                    {
                        System.out.println("player1");
                            if(alreadySelected && board[currentRow][currentColumn] != null && 
                               !board[currentRow][currentColumn].getSelected())
                            {
                                
                                if(board[selectedRow][selectedColumn].getBlackPiece() != board[currentRow][currentColumn].getBlackPiece())
                                {
                                    System.out.println("clicked different color");
                                        moveHappend=true;
//                                        player1 = false;
                                }
                                else
                                {
                                    board[selectedRow][selectedColumn].setSelected(false);
                                    selectedColumn = currentColumn;
                                    selectedRow = currentRow;
                                    board[selectedRow][selectedColumn].setSelected(true);
                                }    
                            }
                            else
                            {
                                if(board[currentRow][currentColumn] != null && 
                                   !board[currentRow][currentColumn].getSelected() && !board[currentRow][currentColumn].getBlackPiece())
                                {
                                    selectedColumn = currentColumn;
                                    selectedRow = currentRow;
                                    board[selectedRow][selectedColumn].setSelected(true);
                                    alreadySelected = true;
                                }
                                else if(board[selectedRow][selectedColumn] != null &&
                                        board[selectedRow][selectedColumn].getSelected() &&
                                        board[currentRow][currentColumn] == null)
                                {
                                    System.out.println("Clicked null");
                                    moveHappend=true;
//                                    player1 = false;
                                }
                                
                            }
                    }
                    else
                    {
                          System.out.println("player2");
                          if(alreadySelected && board[currentRow][currentColumn] != null && 
                               !board[currentRow][currentColumn].getSelected())
                            {
                                if(board[selectedRow][selectedColumn].getBlackPiece() != board[currentRow][currentColumn].getBlackPiece())
                                {
                                    System.out.println("clicked different color");
                                        moveHappend=true;
//                                        player1 = true;
                                }
                                else
                                {
                                    board[selectedRow][selectedColumn].setSelected(false);
                                    selectedColumn = currentColumn;
                                    selectedRow = currentRow;
                                    board[selectedRow][selectedColumn].setSelected(true);
                                }
                            }
                            else
                            {
                                if(board[currentRow][currentColumn] != null && 
                                   !board[currentRow][currentColumn].getSelected() && board[currentRow][currentColumn].getBlackPiece())
                                {
                                    selectedColumn = currentColumn;
                                    selectedRow = currentRow;
                                    board[selectedRow][selectedColumn].setSelected(true);
                                    alreadySelected = true;
                                }
                                else if(board[selectedRow][selectedColumn] != null &&
                                        board[selectedRow][selectedColumn].getSelected() &&
                                        board[currentRow][currentColumn] == null)
                                {
                                    System.out.println("Clicked null");
                                        moveHappend=true;
//                                        player1 = true;
                                }
                            }
                    }
                    }
                }
                if (e.BUTTON3 == e.getButton()) {
                    //right button
                    //if(!IMenu)
                    //reset();
                }
            }
        });
//        addMouseMotionListener(new MouseAdapter() {
//            public void mouseMoved(MouseEvent me) 
//            {
//                if(me.getButton() == me.NOBUTTON)
//                {
//                int xpos = me.getX() - getX(0);
//                int ypos = me.getY() - getY(0);
//                if (xpos < 0 || ypos < 0 || xpos > getWidth2() || ypos > getHeight2())
//                    return;
//                //Calculate the width and height of each board square.
//
//                int BlockHeight = getHeight2()/numRows;
//                int BlockWidth = getWidth2()/numColumns;
//                hoverColumn = xpos/BlockWidth;
//                hoverRow = ypos/BlockHeight;
////                System.out.println("MOUSE MOVED");
//                }
//                repaint();
//            }    
//            });

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.VK_ESCAPE == e.getKeyCode())
                {
                    if(!Menu)
                        Menu=true;
                    else
                        Menu=false;
                }

                repaint();
            }
        });
        init();
        start();
    }

    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }
    
    public void paint(Graphics gOld) {
        if (image == null || xsize != getSize().width || ysize != getSize().height) {
            xsize = getSize().width;
            ysize = getSize().height;
            image = createImage(xsize, ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }  

//fill background
        Color brown=new Color(222,173,115);
        Color Darkbrown=new Color (122,74,51);
        Color background=new Color (87,49,32);
        g.setColor(background);

        g.fillRect(0, 0, xsize, ysize);

        int x[] = {getX(0), getX(getWidth2()), getX(getWidth2()), getX(0), getX(0)};
        int y[] = {getY(0), getY(0), getY(getHeight2()), getY(getHeight2()), getY(0)};
//fill iner background
        g.setColor(brown);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.black);
        g.drawPolyline(x, y, 5);
        
        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }        
//horizontal lines
        g.setColor(Color.gray);
        for (int zi=1;zi<numRows;zi++)
        {
            g.drawLine(getX(0) ,getY(0)+zi*getHeight2()/numRows ,
            getX(getWidth2()) ,getY(0)+zi*getHeight2()/numRows );
        }
//vertical lines
        for (int zi=1;zi<numColumns;zi++)
        {
            g.drawLine(getX(0)+zi*getWidth2()/numColumns ,getY(0) ,
            getX(0)+zi*getWidth2()/numColumns,getY(getHeight2())  );
        }

//black squares
        for (int zrow=0;zrow<numRows;zrow++)
        {
            for (int zcolumn=0;zcolumn<numColumns;zcolumn++)
            {
                if (zrow%2==1)
                {
                    if(zcolumn==0||zcolumn==2||zcolumn==4||zcolumn==6||zcolumn==8)
                    {
                    g.setColor(Darkbrown);
                    g.fillRect(getX(0)+zcolumn*getWidth2()/numColumns,getY(0)+zrow*getHeight2()/numRows,getWidth2()/numColumns,getHeight2()/numRows);         
                    }
                }
                if (zrow%2==0)
                {
                    if(zcolumn==1||zcolumn==3||zcolumn==5||zcolumn==7)
                    {
                    g.setColor(Darkbrown);
                    g.fillRect(getX(0)+zcolumn*getWidth2()/numColumns,getY(0)+zrow*getHeight2()/numRows,getWidth2()/numColumns,getHeight2()/numRows);         
                    }
                }
            }
        }
//        g.setColor(Color.YELLOW);
//        g.setFont(new Font("Monospaced",Font.BOLD,40) );
       // g.drawString("Turn Count = " + turnCount, getWidth2()/2, getHeight2()/2);
        for (int zrow=0;zrow<numRows;zrow++)
        {
            for (int zcolumn=0;zcolumn<numColumns;zcolumn++)
            {
                if (board[zrow][zcolumn] != null)
                {
                  //drawing image for black peices  
                  if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.BKing)
                  {
                    board[zrow][zcolumn].draw(g,IBKing,getX(0)+zcolumn*getWidth2()/numColumns,getY(0)+zrow*getHeight2()/numRows,
                               getWidth2()/numColumns,getHeight2()/numRows);
                  }
                  if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.BQueen)
                  {
                    board[zrow][zcolumn].draw(g,IBQueen,getX(0)+zcolumn*getWidth2()/numColumns,getY(0)+zrow*getHeight2()/numRows,
                               getWidth2()/numColumns,getHeight2()/numRows);
                  }
                  if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.BBishop)
                  {
                    board[zrow][zcolumn].draw(g,IBBishop,getX(0)+zcolumn*getWidth2()/numColumns,getY(0)+zrow*getHeight2()/numRows,
                               getWidth2()/numColumns,getHeight2()/numRows);
                  }
                  if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.BKnight)
                  {
                    board[zrow][zcolumn].draw(g,IBKnight,getX(0)+zcolumn*getWidth2()/numColumns,getY(0)+zrow*getHeight2()/numRows,
                               getWidth2()/numColumns,getHeight2()/numRows);
                  }
                  if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.BRook)
                  {
                    board[zrow][zcolumn].draw(g,IBRook,getX(0)+zcolumn*getWidth2()/numColumns,getY(0)+zrow*getHeight2()/numRows,
                               getWidth2()/numColumns,getHeight2()/numRows);
                  }
                  if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.BPawn)
                  {
                    board[zrow][zcolumn].draw(g,IBPawn,getX(0)+zcolumn*getWidth2()/numColumns,getY(0)+zrow*getHeight2()/numRows,
                               getWidth2()/numColumns,getHeight2()/numRows);
                  }
                  if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.BPriest)
                  {
                    board[zrow][zcolumn].draw(g,IBPriest,getX(0)+zcolumn*getWidth2()/numColumns,getY(0)+zrow*getHeight2()/numRows,
                               getWidth2()/numColumns,getHeight2()/numRows);
                  }
                  
                  //drawing image for white peices 
                  if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.WKing)
                  {
                    board[zrow][zcolumn].draw(g,IWKing,getX(0)+zcolumn*getWidth2()/numColumns,getY(0)+zrow*getHeight2()/numRows,
                               getWidth2()/numColumns,getHeight2()/numRows);
                  }
                  if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.WQueen)
                  {
                    board[zrow][zcolumn].draw(g,IWQueen,getX(0)+zcolumn*getWidth2()/numColumns,getY(0)+zrow*getHeight2()/numRows,
                               getWidth2()/numColumns,getHeight2()/numRows);
                  }
                  if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.WBishop)
                  {
                    board[zrow][zcolumn].draw(g,IWBishop,getX(0)+zcolumn*getWidth2()/numColumns,getY(0)+zrow*getHeight2()/numRows,
                               getWidth2()/numColumns,getHeight2()/numRows);
                  }
                  if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.WKnight)
                  {
                    board[zrow][zcolumn].draw(g,IWKnight,getX(0)+zcolumn*getWidth2()/numColumns,getY(0)+zrow*getHeight2()/numRows,
                               getWidth2()/numColumns,getHeight2()/numRows);
                  }
                  if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.WRook)
                  {
                    board[zrow][zcolumn].draw(g,IWRook,getX(0)+zcolumn*getWidth2()/numColumns,getY(0)+zrow*getHeight2()/numRows,
                               getWidth2()/numColumns,getHeight2()/numRows);
                  }
                  if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.WPawn)
                  {
                    board[zrow][zcolumn].draw(g,IWPawn,getX(0)+zcolumn*getWidth2()/numColumns,getY(0)+zrow*getHeight2()/numRows,
                               getWidth2()/numColumns,getHeight2()/numRows);
                  }
                  if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.WPriest)
                  {
                    board[zrow][zcolumn].draw(g,IWPriest,getX(0)+zcolumn*getWidth2()/numColumns,getY(0)+zrow*getHeight2()/numRows,
                               getWidth2()/numColumns,getHeight2()/numRows);
                  }
                  if(board[selectedRow][selectedColumn] != null && 
                     board[selectedRow][selectedColumn].getPriest())
                  {
                      if(board[zrow][zcolumn].getBlackPiece())
                          g.setColor(Color.WHITE);
                      else
                          g.setColor(Color.BLACK);
                      g.setFont(new Font("Monospaced",Font.BOLD,30));
                      g.drawString(" " + board[zrow][zcolumn].getLoyalty(),
                                   getX(-20)+zcolumn*getWidth2()/numColumns,
                                   getY(-10)+zrow*getHeight2()/numRows + 30);
                  }
                }
            }
        }
//DRAWSTUFF
        if(IMenu)
        {
            g.drawImage(ImgMenu,0,20,getWidth(), getHeight(),null);            
            g.setColor(tColor2);
            g.fillRect(getX(getWidth2()/4),getY((getHeight2()/8)*3), getWidth2()/2,getHeight2()/8 );
            g.setColor(Color.black);
            g.setFont(new Font("Monospaced",Font.BOLD,66) );
            g.drawString("Start Game", getX(getWidth2()/4),getY((getHeight2()/32)*15));
            if(Instrucitons)
            {
                g.setColor(tColor2);
                g.fillRect(getX(getWidth2()/4),getY((getHeight2()/8)*5), getWidth2()/2,getHeight2()/8 );
                g.setColor(Color.black);
                g.setFont(new Font("Monospaced",Font.BOLD,60) );
                g.drawString("Information", getX(getWidth2()/4),getY((getHeight2()/32)*23));
            }
            if(!Instrucitons)
            {
                g.drawImage(ImgMenu,0,20,getWidth(), getHeight(),null); 
                g.setColor(tColor2);
                g.fillRect(getX(0),getY(0),getWidth2(),getHeight2());
                //line 1
                info.line(g, getX(0), getY(60), "A Priest piece has been added:                                          " + g.drawImage(IBPriest,getWidth2()-getWidth2()/4,getYNormal(getHeight2()),100,100,null) + 
                                                                                                                              g.drawImage(IWPriest,getWidth2()-getWidth2()/4+100,getYNormal(getHeight2()),100,100,null));
                info.line(g, getX(0), getY(150), "The priest can convert units depending");
                info.line(g, getX(0), getY(170), "on the targeted unit's loyalty");
                info.line(g, getX(0), getY(260), "Green squares indicate possible movements");
                info.line(g, getX(0), getY(350), "Special moves such as castling and en passant");
                info.line(g, getX(0), getY(370), "are indicated by yellow squares");
                info.line(g, getX(0), getY(460), "Each piece has a certain amount of loyalty");
                info.line(g, getX(0), getY(480), "Avoid low loyalty with your troops");
                info.line(g, getX(0), getY(550), "Loyalty increases by 1 every 6 turns");
                info.line(g, getX(0), getY(570), "Although decreases by 2 when a piece is eaten");
                info.line(g, getX(0), getY(660), "To win one must devour the enemy king");
                info.line(g, getX(0), getY(680), "Press the ESC key to reset the game");
                
                
                ///////////////////////////////
                g.setColor(Color.gray);
                g.fillRect(getX((getWidth2()/8)*3),getY((getHeight2()/8)*7), getWidth2()/4,getHeight2()/8 );
                g.setColor(Color.black);
                g.setFont(new Font("Monospaced",Font.BOLD,60) );
                g.drawString("Back", getX((getWidth2()/32)*13),getY((getHeight2()/32)*31));
            }
        }
        
//        if (gameOver)
//        {
//            g.setColor(Color.BLACK);
//            g.setFont(new Font("Monospaced",Font.BOLD,80) );
//            g.drawString("Game Over", getWidth2()/2-getWidth2()/4, getHeight2()/2);
//        } 
        if(Menu && !IMenu)
        {
//            g.setColor(tColor);
//            g.fillRect(getX(getWidth2()/4),getY(getHeight2()/4), getWidth2()/2,getHeight2()/2 );
            g.setColor(Color.WHITE);
            g.fillRect(getX((100)*3),getY((getHeight2()/8)*3)+50, getWidth2()/4,getHeight2()/4-100 );
            g.setColor(Color.red);
            g.setFont(new Font("Monospaced",Font.BOLD,60));
            g.drawString("Reset", getX((100)*3)+10, getY((getHeight2()/8)*3)+120);
        }
        
        if(pieceSwitcher)
        {
            g.setColor(tColor);
            g.fillRect(getX(getWidth2()/8),getY(getHeight2()/2), (getWidth2()/8)*6 ,getHeight2()/8 );
            if (board[currentRow][currentColumn] != null)
            {
                if(board[currentRow][currentColumn].getPieceType() == Piece.PieceTypes.BPawn)
                {
                    g.drawImage(IBPriest,getX(getWidth2()/8),getY(getHeight2()/2),getWidth2()/8 ,getHeight2()/8 ,null);
                    g.drawImage(IBRook,getX(getWidth2()/4),getY(getHeight2()/2),getWidth2()/8 ,getHeight2()/8 ,null);
                    g.drawImage(IBKnight,getX((getWidth2()/8)*3),getY(getHeight2()/2),getWidth2()/8 ,getHeight2()/8 ,null);
                    g.drawImage(IBBishop,getX(getWidth2()/2),getY(getHeight2()/2),(getWidth2()/8) ,getHeight2()/8 ,null);     
                    g.drawImage(IBQueen,getX((getWidth2()/8)*5),getY(getHeight2()/2),getWidth2()/8 ,getHeight2()/8 ,null);
                    g.drawImage(IBPriest,getX((getWidth2()/8)*6),getY(getHeight2()/2),getWidth2()/8 ,getHeight2()/8 ,null);
                }
                else
                {
                    g.drawImage(IWPriest,getX(getWidth2()/8),getY(getHeight2()/2),getWidth2()/8 ,getHeight2()/8 ,null);
                    g.drawImage(IWRook,getX(getWidth2()/4),getY(getHeight2()/2),getWidth2()/8 ,getHeight2()/8 ,null);
                    g.drawImage(IWKnight,getX((getWidth2()/8)*3),getY(getHeight2()/2),getWidth2()/8 ,getHeight2()/8 ,null);
                    g.drawImage(IWBishop,getX(getWidth2()/2),getY(getHeight2()/2),(getWidth2()/8) ,getHeight2()/8 ,null);     
                    g.drawImage(IWQueen,getX((getWidth2()/8)*5),getY(getHeight2()/2),getWidth2()/8 ,getHeight2()/8 ,null);
                    g.drawImage(IWPriest,getX((getWidth2()/8)*6),getY(getHeight2()/2),getWidth2()/8 ,getHeight2()/8 ,null);
                }
            }
        }
        if(drawConversionSuccess)
        {
            g.setColor(Color.WHITE);
            g.fillRect(getX((100)*0),getY((getHeight2()/8)*3), getWidth2(),getHeight2()/8 );
            g.setColor(Color.GREEN);
            g.setFont(new Font("Monospaced",Font.BOLD,50));
            g.drawString("Conversion Success", getWidth2()/4-80, getHeight2()/2+25);
        }
        if(drawConversionFail)
        {
            g.setColor(Color.WHITE);
            g.fillRect(getX((100)*0),getY((getHeight2()/8)*3), getWidth2(),getHeight2()/8 );
            g.setColor(Color.RED);
            g.setFont(new Font("Monospaced",Font.BOLD,60));
            g.drawString("Conversion Failure", getWidth2()/4-80, getHeight2()/2+25);
            
        }
        if(gameOver)
        {
            if(winState == WinState.PLAYER1)
            {
                g.setColor(Color.WHITE);
                g.fillRect(getX((100)*0),getY((getHeight2()/8)*3), getWidth2(),getHeight2()/8 );
                g.setColor(Color.BLACK);
                g.setFont(new Font("Monospaced",Font.BOLD,60));
                g.drawString("Player 1 has won!!", getWidth2()/4-80, getHeight2()/2+25);
            }
            if(winState == WinState.PLAYER2)
            {
                g.setColor(Color.WHITE);
                g.fillRect(getX((100)*0),getY((getHeight2()/8)*3), getWidth2(),getHeight2()/8 );
                g.setColor(Color.BLACK);
                g.setFont(new Font("Monospaced",Font.BOLD,60));
                g.drawString("Player 2 has won!!", getWidth2()/4-80, getHeight2()/2+25);
            }
            
        }
           //         g.setColor(Color.blue);
           //this works fine          g.fillRect(getX(100*3),getY(100), 100,100);
        if(board[selectedRow][selectedColumn]!=null&&board[selectedRow][selectedColumn].getSelected())
        board[selectedRow][selectedColumn].predictedMove(board, selectedRow, selectedColumn, currentRow, currentColumn, g);
        gOld.drawImage(image, 0, 0, null);
    }


////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = 0.05;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {
        back=true;
        Menu = false;
        IMenu=true;
        Instrucitons=true;
        gameOver=false;
        moveHappend=false;
        player1 = true;
        alreadySelected =false;
        winState = WinState.NONE;
        turnCount = 0;
        selectedRow = 0;
        selectedColumn = 0;
        currentRow = 0;
        currentColumn = 0;
        addLoyalty = 6;
        compareTime = 0;
        timeCount = 0;
        pieceSwitcher = false;
        board = new Piece[numRows][numColumns];
        //draw pieces
        ////draw white pawns//////
        for(int i = 1; i<numColumns-1;i++)
            board[6][i] = new Piece(Piece.PieceTypes.WPawn);
        ////draw white Priests////
        board[6][0] = new Piece(Piece.PieceTypes.WPriest);
        board[6][numColumns-1] = new Piece(Piece.PieceTypes.WPriest);
        ////draw white bishops////
        board[7][2] = new Piece(Piece.PieceTypes.WBishop);
        board[7][5] = new Piece(Piece.PieceTypes.WBishop);
        ////draw white rooks
        board[7][0] = new Piece(Piece.PieceTypes.WRook);
        board[7][7] = new Piece(Piece.PieceTypes.WRook);
        ////draw white knights
        board[7][1] = new Piece(Piece.PieceTypes.WKnight);
        board[7][6] = new Piece(Piece.PieceTypes.WKnight);
        ////Draw white king
        board[7][4] = new Piece(Piece.PieceTypes.WKing);
        ////Draw white qeen
        board[7][3] = new Piece(Piece.PieceTypes.WQueen);
        
        ///////

        ////draw black pawns//////
        for(int i = 1; i<numColumns-1;i++)
            board[1][i] = new Piece(Piece.PieceTypes.BPawn);
        ////draw Black Priests////
        board[1][0] = new Piece(Piece.PieceTypes.BPriest);
        board[1][numColumns-1] = new Piece(Piece.PieceTypes.BPriest);
        ////draw black bishops////
        board[0][2] = new Piece(Piece.PieceTypes.BBishop);
        board[0][5] = new Piece(Piece.PieceTypes.BBishop);
        ////draw black rooks
        board[0][0] = new Piece(Piece.PieceTypes.BRook);
        board[0][7] = new Piece(Piece.PieceTypes.BRook);
        ////draw black knights
        board[0][1] = new Piece(Piece.PieceTypes.BKnight);
        board[0][6] = new Piece(Piece.PieceTypes.BKnight);
        ////Draw black king
        board[0][4] = new Piece(Piece.PieceTypes.BKing);
        ////Draw black qeen
        board[0][3] = new Piece(Piece.PieceTypes.BQueen);
        //resetcode
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {

        if (animateFirstTime) {
            
            if(gameOver)
                return;
            animateFirstTime = false;
            if (xsize != getSize().width || ysize != getSize().height) {
                xsize = getSize().width;
                ysize = getSize().height;
            }
            ImgMenu = Toolkit.getDefaultToolkit().getImage("./ImgMenu.png");
            
            IWPriest = Toolkit.getDefaultToolkit().getImage("./IWPriest.png");
            IBPriest = Toolkit.getDefaultToolkit().getImage("./IBPriest.png");
            IWPawn = Toolkit.getDefaultToolkit().getImage("./IWPawn.png");
            IBPawn = Toolkit.getDefaultToolkit().getImage("./IBPawn.png");
            IWBishop = Toolkit.getDefaultToolkit().getImage("./IWBishop.png");
            IBBishop = Toolkit.getDefaultToolkit().getImage("./IBBishop.png");
            IWRook = Toolkit.getDefaultToolkit().getImage("./IWRook.png");
            IBRook = Toolkit.getDefaultToolkit().getImage("./IBRook.png");
            IWKnight = Toolkit.getDefaultToolkit().getImage("./IWKnight.png");
            IBKnight = Toolkit.getDefaultToolkit().getImage("./IBKnight.png");
            IWKing = Toolkit.getDefaultToolkit().getImage("./IWKing.png");
            IBKing = Toolkit.getDefaultToolkit().getImage("./IBKing.png");
            IWQueen = Toolkit.getDefaultToolkit().getImage("./IWQueen.png");
            IBQueen = Toolkit.getDefaultToolkit().getImage("./IBQueen.png");
            IWPriest = Toolkit.getDefaultToolkit().getImage("./IWPriest.png");
            IBPriest = Toolkit.getDefaultToolkit().getImage("./IBPriest.png");
            reset();
        }
        //
        //animate code
        //
        if (!IMenu)
        {
            if (moveHappend&&board[selectedRow][selectedColumn]!=null)
            {
                board[selectedRow][selectedColumn].setSelected(false);
                if(board[selectedRow][selectedColumn].move(board, selectedRow, selectedColumn,
                                                        currentRow, currentColumn, turnCount))
                {
                    player1 = !player1;
                    if ((currentRow==0&&board[selectedRow][selectedColumn].getPieceType() == Piece.PieceTypes.WPawn) ||
                        (currentRow==7&&board[selectedRow][selectedColumn].getPieceType() == Piece.PieceTypes.BPawn))
                    {
                        pieceSwitcher=true;
                        tempRow=currentRow;
                        tempCol=currentColumn;
                    }
                    if(board[currentRow][currentColumn] != null && !Piece.mindControlled)
                    {
                        if(board[currentRow][currentColumn].getPieceType() == Piece.PieceTypes.BKing)
                        {
                            winState = WinState.PLAYER1;
                            gameOver = true;
                        }
                        else if(board[currentRow][currentColumn].getPieceType() == Piece.PieceTypes.WKing)
                        {
                            winState = WinState.PLAYER2;
                            gameOver = true;
                        }
                    }
                    if(!Piece.mindControlled && !board[selectedRow][selectedColumn].getPriest())
                    {
                        if(board[currentRow][currentColumn] != null)
                        {
                            for (int zrow=0;zrow<numRows;zrow++)
                            {
                                for (int zcolumn=0;zcolumn<numColumns;zcolumn++)
                                {
                                    if(board[zrow][zcolumn] != null)
                                    {
                                        if(board[currentRow][currentColumn].getBlackPiece() ==
                                           board[zrow][zcolumn].getBlackPiece())
                                        {
                                            board[zrow][zcolumn].setLoyalty(board[zrow][zcolumn].getLoyalty()-2);
                                            if(board[zrow][zcolumn].getLoyalty() <= 0)
                                            {
                                                board[zrow][zcolumn].setLoyalty(1);
                                            }
                                            if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.BKing)
                                                board[zrow][zcolumn].setLoyalty(10);
                                        }
                                        else if(!board[currentRow][currentColumn].getBlackPiece() == 
                                                !board[zrow][zcolumn].getBlackPiece())
                                        {
                                            board[zrow][zcolumn].setLoyalty(board[zrow][zcolumn].getLoyalty()-2);
                                            if(board[zrow][zcolumn].getLoyalty() <= 0)
                                            {
                                                board[zrow][zcolumn].setLoyalty(1);
                                            }
                                            if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.WKing)
                                                board[zrow][zcolumn].setLoyalty(10);
                                        }
                                    }
                                }
                            }
                        }
                        board[currentRow][currentColumn]=board[selectedRow][selectedColumn];
                        board[selectedRow][selectedColumn]=null;
                    }
                    else if(!Piece.mindControlled && board[selectedRow][selectedColumn].getPriest() &&
                            board[currentRow][currentColumn] == null)
                    {
                        board[currentRow][currentColumn]=board[selectedRow][selectedColumn];
                        board[selectedRow][selectedColumn]=null;
                    }
                    else
                    {
                        //mind controlled
                        if(!drawConversionFail)
                            drawConversionSuccess = true;
                        Piece.mindControlled = false;
                    }
                    turnCount++;
                    if(turnCount == addLoyalty)
                    {
                        for (int zrow=0;zrow<numRows;zrow++)
                            {
                                for (int zcolumn=0;zcolumn<numColumns;zcolumn++)
                                {
                                    if(board[zrow][zcolumn] != null)
                                    {
                                        board[zrow][zcolumn].setLoyalty(board[zrow][zcolumn].getLoyalty()+1);
                                        if(board[zrow][zcolumn].getLoyalty() > 9)
                                            board[zrow][zcolumn].setLoyalty(9);
                                        if(board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.BKing ||
                                           board[zrow][zcolumn].getPieceType() == Piece.PieceTypes.WKing)
                                                board[zrow][zcolumn].setLoyalty(10);
                                    }
                                }
                            }
                        addLoyalty+=6;
                    }
                }
                alreadySelected = false;
                click = new sound("GoodSound.wav");
                moveHappend=false;
            }
            if(drawConversionSuccess)
            {
                timeCount++;
                if(timeCount %25==24)
                {
                    drawConversionSuccess = false;
                    timeCount=0;
                }
            }
            if(drawConversionFail)
            {
                timeCount++;
                if(timeCount %25==24)
                {
                    drawConversionFail = false;
                    timeCount=0;
                }
            }
        }
        
    }
////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
/////////////////////////////////////////////////////////////////////////
    static public int getX(int x) {
        return (x + XBORDER + WINDOW_BORDER);
    }

   static public int getY(int y) {
        return (y + YBORDER + YTITLE );
    }
    static public int getYNormal(int y) {
        return (-y + YBORDER + YTITLE + getHeight2());
    }
    
    static public int getWidth2() {
        return (xsize - 2 * (XBORDER + WINDOW_BORDER));
    }

   static public int getHeight2() {
        return (ysize - 2 * YBORDER - WINDOW_BORDER - YTITLE);
    }
}
