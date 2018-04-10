package chess;

import java.awt.*;

public class Piece {
    private Color color;
    private int loyalty = 0;
    private boolean blackPiece = false;
    private boolean firstMove = true;
    private boolean movedtwo=false;
    private boolean selected = false;
    public static boolean mindControlled = false;
    private boolean priest = false;
    private int pieceTurnCount = -2;
    Color clearG = new Color (51,255,51,80);
    Color clearY = new Color  (254,239,97,200);
    enum PieceTypes
    {
        BPawn,BKnight,BBishop,BRook,BQueen,BKing,BPriest,
        WPawn,WKnight,WBishop,WRook,WQueen,WKing,WPriest,     
    }
    private PieceTypes pieceType;
 
    
    
    Piece(PieceTypes _pieceType) 
    {
        loyalty=(int)(Math.random()*3+7);

        selected = false;
        pieceType = _pieceType;
        if(_pieceType == PieceTypes.BKing ||
           _pieceType == PieceTypes.WKing)
            loyalty = 10;
        if(_pieceType == PieceTypes.BBishop ||
           _pieceType == PieceTypes.BKnight ||
           _pieceType == PieceTypes.BPawn ||
           _pieceType == PieceTypes.BRook || 
           _pieceType == PieceTypes.BQueen || 
           _pieceType == PieceTypes.BKing ||
           _pieceType == PieceTypes.BPriest     )
            blackPiece = true;
        
        if(_pieceType == PieceTypes.BPriest ||
           _pieceType == PieceTypes.WPriest)
            priest = true;
        
    }
    PieceTypes getPieceType()
    {
        return(pieceType);
    } 
    void setPieceType(PieceTypes _pieceType)
    {
        pieceType = _pieceType;
    }   
    void setLoyalty(int _loyalty)
    {
        loyalty = _loyalty;
    }
    int getLoyalty()
    {
        return(loyalty);
    }
    void setSelected(boolean _selected)
    {
        selected = _selected;
    }
    boolean getSelected()
    {
        return(selected);
    }
    boolean getBlackPiece()
    {
        return(blackPiece);
    }
    void setPriest(boolean _priest)
    {
        priest = _priest;
    }
    boolean getPriest()
    {
        return(priest);
    }
    ////
    //
    //
    ////
    PieceTypes mindControl(PieceTypes  _pieceType, boolean _blackPiece)
    {
        if(loyalty-1 == (int)(Math.random() * loyalty))
        {
            if(_blackPiece)
            {
                if(_pieceType == PieceTypes.WPawn)
                {
                    mindControlled = true;
                    return(PieceTypes.BPawn);
                }
                if(_pieceType == PieceTypes.WBishop)
                {
                    mindControlled = true;
                    return(PieceTypes.BBishop);
                }
                if(_pieceType == PieceTypes.WKnight)
                {
                    mindControlled = true;
                    return(PieceTypes.BKnight);
                }
                if(_pieceType == PieceTypes.WRook)
                {
                    mindControlled = true;
                    return(PieceTypes.BRook);
                }
                if(_pieceType == PieceTypes.WQueen)
                {
                    mindControlled = true;
                    return(PieceTypes.BQueen);
                }
                if(_pieceType == PieceTypes.WPriest)
                {
                    mindControlled = true;
                    return(PieceTypes.BPriest);
                }
            }
            else
            {
                if(_pieceType == PieceTypes.BPawn)
                {
                    mindControlled = true;
                    return(PieceTypes.WPawn);
                }
                if(_pieceType == PieceTypes.BBishop)
                {
                    mindControlled = true;
                    return(PieceTypes.WBishop);
                }
                if(_pieceType == PieceTypes.BKnight)
                {
                    mindControlled = true;
                    return(PieceTypes.WKnight);
                }
                if(_pieceType == PieceTypes.BRook)
                {
                    mindControlled = true;
                    return(PieceTypes.WRook);
                }
                if(_pieceType == PieceTypes.BQueen)
                { 
                    mindControlled = true;
                    return(PieceTypes.WQueen);
                }
                if(_pieceType == PieceTypes.BPriest)
                {
                    mindControlled = true;
                    return(PieceTypes.WPriest);
                }
            }
        }
        Chess.drawConversionFail = true;
        return(_pieceType);
    }
    static boolean pieceSwitcher(Piece board[][],int currentRow, int currentColumn, int tempRow, int tempCol, boolean pieceSwitcher)
    {
        while(pieceSwitcher)
        {
            if(currentRow==4&&currentColumn==1||currentRow==4&&currentColumn==6)
            {
                if (board[tempRow][tempCol].getBlackPiece())
                {
                    board[tempRow][tempCol].setPriest(true);
                    board[tempRow][tempCol].setPieceType(Piece.PieceTypes.BPriest);
                }
                else
                {
                    board[tempRow][tempCol].setPriest(true);
                    board[tempRow][tempCol].setPieceType(Piece.PieceTypes.WPriest);
                }
                pieceSwitcher=false;
            }
            if((currentRow==4&&currentColumn==2))
            {
                if (board[tempRow][tempCol].getBlackPiece())
                    board[tempRow][tempCol].setPieceType(Piece.PieceTypes.BRook);
                else
                    board[tempRow][tempCol].setPieceType(Piece.PieceTypes.WRook);
                pieceSwitcher=false;
            }
            if((currentRow==4&&currentColumn==3))
            {
                if (board[tempRow][tempCol].getBlackPiece())
                    board[tempRow][tempCol].setPieceType(Piece.PieceTypes.BKnight);
                else
                    board[tempRow][tempCol].setPieceType(Piece.PieceTypes.WKnight);
                pieceSwitcher=false;
            }
            if((currentRow==4&&currentColumn==4))
            {
                if (board[tempRow][tempCol].getBlackPiece())
                    board[tempRow][tempCol].setPieceType(Piece.PieceTypes.BBishop);
                else
                    board[tempRow][tempCol].setPieceType(Piece.PieceTypes.WBishop);
                pieceSwitcher=false;
            }
            if((currentRow==4&&currentColumn==5))
            {
                if (board[tempRow][tempCol].getBlackPiece())
                    board[tempRow][tempCol].setPieceType(Piece.PieceTypes.BQueen);
                else
                    board[tempRow][tempCol].setPieceType(Piece.PieceTypes.WQueen);
                pieceSwitcher=false;
            }
        }
        return(pieceSwitcher);
    }
    
    ////
    
      void predictedMove(Piece board[][],int selectedRow, int selectedCol, int currentRow, int currentCol,Graphics2D g)               
        {
            g.setColor(clearG);
            ////////////////////
//Prediction4Pawns            
            if(pieceType == PieceTypes.WPawn)
            {
                if(firstMove)
                    {
                        if(board[selectedRow-1][selectedCol]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                        if(board[selectedRow-1][selectedCol]==null&&board[selectedRow-2][selectedCol]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-2)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                        if(selectedCol-1>-1 && board[selectedRow-1][selectedCol-1]!=null&&board[selectedRow-1][selectedCol-1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                        if(selectedCol+1<8 && board[selectedRow-1][selectedCol+1]!=null&&board[selectedRow-1][selectedCol+1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                    }
                else
                {
                     if(selectedRow-1>-1&&board[selectedRow-1][selectedCol]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                        if(selectedRow-1>-1&&selectedCol-1>-1&&board[selectedRow-1][selectedCol-1]!=null&&board[selectedRow-1][selectedCol-1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                        if(selectedRow-1>-1&&selectedCol+1<8&&board[selectedRow-1][selectedCol+1]!=null&&board[selectedRow-1][selectedCol+1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                        if(board[selectedRow][selectedCol].movedtwo)
                        {
                            if(selectedCol+1<8 && board[selectedRow][selectedCol+1] != null && board[selectedRow][selectedCol].pieceTurnCount == board[selectedRow][selectedCol+1].pieceTurnCount)
                            {
                                g.setColor(clearY);
                                g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            }
                           if( selectedCol-1>-1&&board[selectedRow][selectedCol-1] != null && board[selectedRow][selectedCol].pieceTurnCount == board[selectedRow][selectedCol-1].pieceTurnCount)
                           {
                                g.setColor(clearY);
                                g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                           }
                        }
                }
            }
//Black//Pawn//
            if(pieceType == PieceTypes.BPawn)
            {
               if(firstMove)
                    {
                        if(board[selectedRow+1][selectedCol]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            if(board[selectedRow+2][selectedCol]==null)
                            {
                                g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+2)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            }
                        }
                        if(selectedCol-1>-1 && board[selectedRow+1][selectedCol-1]!=null&&!board[selectedRow+1][selectedCol-1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                        if(selectedCol+1<8 && board[selectedRow+1][selectedCol+1]!=null&&!board[selectedRow+1][selectedCol+1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                    }
                else
                {
                     if(selectedRow+1<8&&board[selectedRow+1][selectedCol]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                        if(selectedRow+1<8&&selectedCol-1>-1&&board[selectedRow+1][selectedCol-1]!=null&&!board[selectedRow+1][selectedCol-1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                        if(selectedRow+1<8&&selectedCol+1<8&&board[selectedRow+1][selectedCol+1]!=null&&!board[selectedRow+1][selectedCol+1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                        if(board[selectedRow][selectedCol].movedtwo)
                        {
                            if(selectedCol+1<8 && board[selectedRow][selectedCol+1] != null && board[selectedRow][selectedCol].pieceTurnCount == board[selectedRow][selectedCol+1].pieceTurnCount)
                            {
                            g.setColor(clearY);    
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            }
                           if( selectedCol-1>-1&&board[selectedRow][selectedCol-1] != null && board[selectedRow][selectedCol].pieceTurnCount == board[selectedRow][selectedCol-1].pieceTurnCount)
                           {
                            g.setColor(clearY);
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                           }
                        }
                        
                }
            }
//Prediction4Rooks            
            if(pieceType == PieceTypes.WRook||pieceType == PieceTypes.BRook)
            {
//Up                
                int i=1;   
                while(selectedRow-i>-1&&board[selectedRow-i][selectedCol]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            i++;
                        }
                if(pieceType == PieceTypes.WRook)
                {
                    if(selectedRow-i>-1&&board[selectedRow-i][selectedCol]!=null&&board[selectedRow-i][selectedCol].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                }
                else
                    if(selectedRow-i>-1&&board[selectedRow-i][selectedCol]!=null&&!board[selectedRow-i][selectedCol].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
//Down                
                i=1;
                while(i<8&&selectedRow+i<8&&board[selectedRow+i][selectedCol]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            i++;
                        }
                if(pieceType == PieceTypes.WRook)
                {
                    if(selectedRow+i<8&&board[selectedRow+i][selectedCol]!=null&&board[selectedRow+i][selectedCol].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                }
                else
                        if(selectedRow+i<8&&board[selectedRow+i][selectedCol]!=null&&!board[selectedRow+i][selectedCol].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
//Left                
                i=1;
                while(selectedCol-i>-1&&board[selectedRow][selectedCol-i]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            i++;
                        }
                if(pieceType == PieceTypes.WRook)
                {
                    if(selectedCol-i>-1&&board[selectedRow][selectedCol-i]!=null&&board[selectedRow][selectedCol-i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                }
                else
                    if(selectedCol-i>-1&&board[selectedRow][selectedCol-i]!=null&&!board[selectedRow][selectedCol-i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
//Right                
                i=1;
                while(i<8&&selectedCol+i<8&&board[selectedRow][selectedCol+i]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            i++;
                        }
                if(pieceType == PieceTypes.WRook)
                {
                    if(selectedCol+i<8&&board[selectedRow][selectedCol+i]!=null&&board[selectedRow][selectedCol+i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                }
                else
                    if(selectedCol+i<8&&board[selectedRow][selectedCol+i]!=null&&!board[selectedRow][selectedCol+i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }  
                
            }
//Prediction4Knights
            if(pieceType == PieceTypes.WKnight)
                {
                    if(selectedRow-2>-1 && selectedRow-2<8 && selectedCol-1>=0 && selectedCol-1<8 && (board[selectedRow-2][selectedCol-1] == null||board[selectedRow-2][selectedCol-1]!=null&&board[selectedRow-2][selectedCol-1].getBlackPiece()))
                    {
                      g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-2)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                    if(selectedRow-2>-1 && selectedRow-2<8 && selectedCol+1>=0 && selectedCol+1<8 && (board[selectedRow-2][selectedCol+1] == null||board[selectedRow-2][selectedCol+1]!=null&&board[selectedRow-2][selectedCol+1].getBlackPiece()))
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-2)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                    if(selectedRow+2>-1 && selectedRow+2<8 && selectedCol-1>=0 && selectedCol-1<8 && (board[selectedRow+2][selectedCol-1] == null||board[selectedRow+2][selectedCol-1]!=null&&board[selectedRow+2][selectedCol-1].getBlackPiece()))
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+2)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                    if(selectedRow+2>-1 && selectedRow+2<8 && selectedCol+1>=0 && selectedCol+1<8 && (board[selectedRow+2][selectedCol+1] == null||board[selectedRow+2][selectedCol+1]!=null&&board[selectedRow+2][selectedCol+1].getBlackPiece()))
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+2)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                    if(selectedRow-1>-1 && selectedRow-1<8 && selectedCol-2>=0 && selectedCol-2<8 && (board[selectedRow-1][selectedCol-2] == null||board[selectedRow-1][selectedCol-2]!=null&&board[selectedRow-1][selectedCol-2].getBlackPiece()))
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-2)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                    if(selectedRow-1>-1 && selectedRow-1<8 && selectedCol+2>=0 && selectedCol+2<8 && (board[selectedRow-1][selectedCol+2] == null||board[selectedRow-1][selectedCol+2]!=null&&board[selectedRow-1][selectedCol+2].getBlackPiece()))
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+2)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                    if(selectedRow+1>-1 && selectedRow+1<8 && selectedCol-2>=0 && selectedCol-2<8 && (board[selectedRow+1][selectedCol-2] == null||board[selectedRow+1][selectedCol-2]!=null&&board[selectedRow+1][selectedCol-2].getBlackPiece()))
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-2)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                    if(selectedRow+1>-1 && selectedRow+1<8 && selectedCol+2>=0 && selectedCol+2<8 && (board[selectedRow+1][selectedCol+2] == null||board[selectedRow+1][selectedCol+2]!=null&&board[selectedRow+1][selectedCol+2].getBlackPiece()))
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+2)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                }                       
            if(pieceType == PieceTypes.BKnight)
                {
                    if(selectedRow-2>-1 && selectedRow-2<8 && selectedCol-1>=0 && selectedCol-1<8 && (board[selectedRow-2][selectedCol-1] == null||board[selectedRow-2][selectedCol-1]!=null&&!board[selectedRow-2][selectedCol-1].getBlackPiece()))
                    {
                      g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-2)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                    if(selectedRow-2>-1 && selectedRow-2<8 && selectedCol+1>=0 && selectedCol+1<8 && (board[selectedRow-2][selectedCol+1] == null||board[selectedRow-2][selectedCol+1]!=null&&!board[selectedRow-2][selectedCol+1].getBlackPiece()))
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-2)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                    if(selectedRow+2>-1 && selectedRow+2<8 && selectedCol-1>=0 && selectedCol-1<8 && (board[selectedRow+2][selectedCol-1] == null||board[selectedRow+2][selectedCol-1]!=null&&!board[selectedRow+2][selectedCol-1].getBlackPiece()))
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+2)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                    if(selectedRow+2>-1 && selectedRow+2<8 && selectedCol+1>=0 && selectedCol+1<8 && (board[selectedRow+2][selectedCol+1] == null||board[selectedRow+2][selectedCol+1]!=null&&!board[selectedRow+2][selectedCol+1].getBlackPiece()))
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+2)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                    if(selectedRow-1>-1 && selectedRow-1<8 && selectedCol-2>=0 && selectedCol-2<8 && (board[selectedRow-1][selectedCol-2] == null||board[selectedRow-1][selectedCol-2]!=null&&!board[selectedRow-1][selectedCol-2].getBlackPiece()))
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-2)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                    if(selectedRow-1>-1 && selectedRow-1<8 && selectedCol+2>=0 && selectedCol+2<8 && (board[selectedRow-1][selectedCol+2] == null||board[selectedRow-1][selectedCol+2]!=null&&!board[selectedRow-1][selectedCol+2].getBlackPiece()))
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+2)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                    if(selectedRow+1>-1 && selectedRow+1<8 && selectedCol-2>=0 && selectedCol-2<8 && (board[selectedRow+1][selectedCol-2] == null||board[selectedRow+1][selectedCol-2]!=null&&!board[selectedRow+1][selectedCol-2].getBlackPiece()))
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-2)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                    if(selectedRow+1>-1 && selectedRow+1<8 && selectedCol+2>=0 && selectedCol+2<8 && (board[selectedRow+1][selectedCol+2] == null||board[selectedRow+1][selectedCol+2]!=null&&!board[selectedRow+1][selectedCol+2].getBlackPiece()))
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+2)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                }
//Prediction4Bishops            
            if(pieceType == PieceTypes.WBishop||pieceType == PieceTypes.BBishop)
            {
//UpRight               
                int i=1;   
                while(selectedCol+i<8&&selectedRow-i>-1&&board[selectedRow-i][selectedCol+i]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            i++;
                        }
                if(pieceType == PieceTypes.WBishop)
                {
                    if(selectedCol+i<8&&selectedRow-i>-1&&board[selectedRow-i][selectedCol+i]!=null&&board[selectedRow-i][selectedCol+i].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                }
                else
                    if(selectedCol+i<8&&selectedRow-i>-1&&board[selectedRow-i][selectedCol]!=null&&!board[selectedRow-i][selectedCol+i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
//DownRight
                i=1;
                while(selectedCol+i<8&&selectedRow+i<8&&board[selectedRow+i][selectedCol+i]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            i++;
                        }
                if(pieceType == PieceTypes.WBishop)
                {
                    if(selectedCol+i<8&&selectedRow+i<8&&board[selectedRow+i][selectedCol+i]!=null&&board[selectedRow+i][selectedCol+i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                }
                else
                        if(selectedCol+i<8&&selectedRow+i<8&&board[selectedRow+i][selectedCol+i]!=null&&!board[selectedRow+i][selectedCol+i].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
//DownLeft                
                i=1;      
                while(selectedRow+i<8&&selectedCol-i>-1&&board[selectedRow+i][selectedCol-i]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            i++;
                        }
                if(pieceType == PieceTypes.WBishop)
                {
                    if(selectedRow+i<8&&selectedCol-i>-1&&board[selectedRow+i][selectedCol-i]!=null&&board[selectedRow+i][selectedCol-i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                }
                else
                    if(selectedRow+i<8&&selectedCol-i>-1&&board[selectedRow+i][selectedCol-i]!=null&&!board[selectedRow+i][selectedCol-i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
//UpLeft                
                i=1;
                while(selectedRow-i>-1&&selectedCol-i>-1&&board[selectedRow-i][selectedCol-i]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            i++;
                        }
                if(pieceType == PieceTypes.WBishop)
                {
                    if(selectedRow-i>-1&&selectedCol-i>-1&&board[selectedRow-i][selectedCol-i]!=null&&board[selectedRow-i][selectedCol-i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                }
                else
                    if(selectedRow-i>-1&&selectedCol-i>-1&&board[selectedRow-i][selectedCol-i]!=null&&!board[selectedRow-i][selectedCol-i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
            }
///////////////////            
//Prediction4Queens
            if(pieceType == PieceTypes.WQueen||pieceType == PieceTypes.BQueen)
            {
                
//Up                
                int i=1;   
                while(selectedRow-i>-1&&board[selectedRow-i][selectedCol]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            i++;
                        }
                if(pieceType == PieceTypes.WQueen)
                {
                    if(selectedRow-i>-1&&board[selectedRow-i][selectedCol]!=null&&board[selectedRow-i][selectedCol].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                }
                else
                    if(selectedRow-i>-1&&board[selectedRow-i][selectedCol]!=null&&!board[selectedRow-i][selectedCol].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
//Down                
                i=1;
                while(i<8&&selectedRow+i<8&&board[selectedRow+i][selectedCol]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            i++;
                        }
                if(pieceType == PieceTypes.WQueen)
                {
                    if(selectedRow+i<8&&board[selectedRow+i][selectedCol]!=null&&board[selectedRow+i][selectedCol].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                }
                else
                        if(selectedRow+i<8&&board[selectedRow+i][selectedCol]!=null&&!board[selectedRow+i][selectedCol].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
//Left                
                i=1;
                while(selectedCol-i>-1&&board[selectedRow][selectedCol-i]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            i++;
                        }
                if(pieceType == PieceTypes.WQueen)
                {
                    if(selectedCol-i>-1&&board[selectedRow][selectedCol-i]!=null&&board[selectedRow][selectedCol-i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                }
                else
                    if(selectedCol-i>-1&&board[selectedRow][selectedCol-i]!=null&&!board[selectedRow][selectedCol-i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
//Right                
                i=1;
                while(i<8&&selectedCol+i<8&&board[selectedRow][selectedCol+i]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            i++;
                        }
                if(pieceType == PieceTypes.WQueen)
                {
                    if(selectedCol+i<8&&board[selectedRow][selectedCol+i]!=null&&board[selectedRow][selectedCol+i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                }
                else
                    if(selectedCol+i<8&&board[selectedRow][selectedCol+i]!=null&&!board[selectedRow][selectedCol+i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }  
                
//UpRight               
                i=1;   
                while(selectedCol+i<8&&selectedRow-i>-1&&board[selectedRow-i][selectedCol+i]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            i++;
                        }
                if(pieceType == PieceTypes.WQueen)
                {
                    if(selectedCol+i<8&&selectedRow-i>-1&&board[selectedRow-i][selectedCol+i]!=null&&board[selectedRow-i][selectedCol+i].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                }
                else
                    if(selectedCol+i<8&&selectedRow-i>-1&&board[selectedRow-i][selectedCol]!=null&&!board[selectedRow-i][selectedCol+i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
//DownRight
                i=1;
                while(selectedCol+i<8&&selectedRow+i<8&&board[selectedRow+i][selectedCol+i]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            i++;
                        }
                if(pieceType == PieceTypes.WQueen)
                {
                    if(selectedCol+i<8&&selectedRow+i<8&&board[selectedRow+i][selectedCol+i]!=null&&board[selectedRow+i][selectedCol+i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                }
                else
                        if(selectedCol+i<8&&selectedRow+i<8&&board[selectedRow+i][selectedCol+i]!=null&&!board[selectedRow+i][selectedCol+i].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
//DownLeft                
                i=1;      
                while(selectedRow+i<8&&selectedCol-i>-1&&board[selectedRow+i][selectedCol-i]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            i++;
                        }
                if(pieceType == PieceTypes.WQueen)
                {
                    if(selectedRow+i<8&&selectedCol-i>-1&&board[selectedRow+i][selectedCol-i]!=null&&board[selectedRow+i][selectedCol-i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                }
                else
                    if(selectedRow+i<8&&selectedCol-i>-1&&board[selectedRow+i][selectedCol-i]!=null&&!board[selectedRow+i][selectedCol-i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
//UpLeft                
                i=1;
                while(selectedRow-i>-1&&selectedCol-i>-1&&board[selectedRow-i][selectedCol-i]==null)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            i++;
                        }
                if(pieceType == PieceTypes.WQueen)
                {
                    if(selectedRow-i>-1&&selectedCol-i>-1&&board[selectedRow-i][selectedCol-i]!=null&&board[selectedRow-i][selectedCol-i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
                }
                else
                    if(selectedRow-i>-1&&selectedCol-i>-1&&board[selectedRow-i][selectedCol-i]!=null&&!board[selectedRow-i][selectedCol-i].blackPiece)
                    {
                        g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-i)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-i)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                    }
            }
//Prediction4Kings
            if(pieceType == PieceTypes.WKing)
            {
                //up
                if(selectedRow-1>-1&&board[selectedRow-1][selectedCol]==null||selectedRow-1>-1&&board[selectedRow-1][selectedCol]!=null&&board[selectedRow-1][selectedCol].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                //down
                if(selectedRow+1<8&&board[selectedRow+1][selectedCol]==null||selectedRow+1<8&&board[selectedRow+1][selectedCol]!=null&&board[selectedRow+1][selectedCol].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                //left
                if(selectedCol-1>-1&&board[selectedRow][selectedCol-1]==null||selectedCol-1>-1&&board[selectedRow][selectedCol-1]!=null&&board[selectedRow][selectedCol-1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                //right
                if(selectedCol+1<8&&board[selectedRow][selectedCol+1]==null||selectedCol+1<8&&board[selectedRow][selectedCol+1]!=null&&board[selectedRow][selectedCol+1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                //upright
                if(selectedCol+1<8&&selectedRow-1>-1&&board[selectedRow-1][selectedCol+1]==null||selectedCol+1<8&&selectedRow-1>-1&&board[selectedRow-1][selectedCol+1]!=null&&board[selectedRow-1][selectedCol+1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                //downright
                if(selectedCol+1<8&&selectedRow+1<8&&board[selectedRow+1][selectedCol+1]==null||selectedCol+1<8&&selectedRow+1<8&&board[selectedRow+1][selectedCol+1]!=null&&board[selectedRow+1][selectedCol+1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                //upleft
                if(selectedRow-1>-1&&selectedCol-1>-1&&board[selectedRow-1][selectedCol-1]==null||selectedRow-1>-1&&selectedCol-1>-1&&board[selectedRow-1][selectedCol-1]!=null&&board[selectedRow-1][selectedCol-1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                //Downleft
                if(selectedRow+1<8&&selectedCol-1>-1&&board[selectedRow+1][selectedCol-1]==null||selectedRow+1<8&&selectedCol-1>-1&&board[selectedRow+1][selectedCol-1]!=null&&board[selectedRow+1][selectedCol-1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                if(selectedCol+3<8&&
                   board[selectedRow][selectedCol]!=null&&
                   board[selectedRow][selectedCol].firstMove&&
                   board[selectedRow][selectedCol+1]==null&&
                   board[selectedRow][selectedCol+2]==null&&
                   board[selectedRow][selectedCol+3]!=null&&
                   board[selectedRow][selectedCol+3].firstMove)
                {
                    g.setColor(clearY);
                    g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+2)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                }
                if(selectedCol-4>-1&&
                   board[selectedRow][selectedCol].firstMove&&
                   board[selectedRow][selectedCol-1]==null&&
                   board[selectedRow][selectedCol-2]==null&&
                   board[selectedRow][selectedCol-3]==null&&
                   board[selectedRow][selectedCol-4]!=null&&
                   board[selectedRow][selectedCol-4].firstMove)
                {
                    g.setColor(clearY);
                    g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-3)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                }
            }
            if(pieceType == PieceTypes.BKing)
            {
                //up
                if(selectedRow-1>-1&&board[selectedRow-1][selectedCol]==null||selectedRow-1>-1&&board[selectedRow-1][selectedCol]!=null&&!board[selectedRow-1][selectedCol].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                //down
                if(selectedRow+1<8&&board[selectedRow+1][selectedCol]==null||selectedRow+1<8&&board[selectedRow+1][selectedCol]!=null&&!board[selectedRow+1][selectedCol].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                //left
                if(selectedCol-1>-1&&board[selectedRow][selectedCol-1]==null||selectedCol-1>-1&&board[selectedRow][selectedCol-1]!=null&&!board[selectedRow][selectedCol-1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                //right
                if(selectedCol+1<8&&board[selectedRow][selectedCol+1]==null||selectedCol+1<8&&board[selectedRow][selectedCol+1]!=null&&!board[selectedRow][selectedCol+1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                //upright
                if(selectedCol+1<8&&selectedRow-1>-1&&board[selectedRow-1][selectedCol+1]==null||selectedCol+1<8&&selectedRow-1>-1&&board[selectedRow-1][selectedCol+1]!=null&&!board[selectedRow-1][selectedCol+1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                //downright
                if(selectedCol+1<8&&selectedRow+1<8&&board[selectedRow+1][selectedCol+1]==null||selectedCol+1<8&&selectedRow+1<8&&board[selectedRow+1][selectedCol+1]!=null&&!board[selectedRow+1][selectedCol+1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                //upleft
                if(selectedRow-1>-1&&selectedCol-1>-1&&board[selectedRow-1][selectedCol-1]==null||selectedRow-1>-1&&selectedCol-1>-1&&board[selectedRow-1][selectedCol-1]!=null&&!board[selectedRow-1][selectedCol-1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                //Downleft
                if(selectedRow+1<8&&selectedCol-1>-1&&board[selectedRow+1][selectedCol-1]==null||selectedRow+1<8&&selectedCol-1>-1&&board[selectedRow+1][selectedCol-1]!=null&&!board[selectedRow+1][selectedCol-1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
                if(selectedCol+3<8&&
                   board[selectedRow][selectedCol]!=null&&
                   board[selectedRow][selectedCol].firstMove&&
                   board[selectedRow][selectedCol+1]==null&&
                   board[selectedRow][selectedCol+2]==null&&
                   board[selectedRow][selectedCol+3]!=null&&
                   board[selectedRow][selectedCol+3].firstMove)
                {
                    g.setColor(clearY);
                    g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+2)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                }
                if(selectedCol-4>-1&&
                   board[selectedRow][selectedCol].firstMove&&
                   board[selectedRow][selectedCol-1]==null&&
                   board[selectedRow][selectedCol-2]==null&&
                   board[selectedRow][selectedCol-3]==null&&
                   board[selectedRow][selectedCol-4]!=null&&
                   board[selectedRow][selectedCol-4].firstMove)
                {
                    g.setColor(clearY);
                    g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-3)),Chess.getY((Chess.getHeight2()/8)*(selectedRow)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                }
            }
            ////////
            if(pieceType == PieceTypes.WPriest)
            {
//UpRight               
                  
                if(selectedCol+1<8&&selectedRow-1>-1&&board[selectedRow-1][selectedCol+1]==null||selectedCol+1<8&&selectedRow-1>-1&&board[selectedRow-1][selectedCol+1]!=null&&board[selectedRow-1][selectedCol+1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }

//DownRight
                
                if(selectedCol+1<8&&selectedRow+1<8&&board[selectedRow+1][selectedCol+1]==null||selectedCol+1<8&&selectedRow+1<8&&board[selectedRow+1][selectedCol+1]!=null&&board[selectedRow+1][selectedCol+1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            
                        }
                
//DownLeft                
                   
                if(selectedRow+1<8&&selectedCol-1>-1&&board[selectedRow+1][selectedCol-1]==null||selectedRow+1<8&&selectedCol-1>-1&&board[selectedRow+1][selectedCol-1]!=null&&board[selectedRow+1][selectedCol-1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
               
//UpLeft                
                
                if(selectedRow-1>-1&&selectedCol-1>-1&&board[selectedRow-1][selectedCol-1]==null||selectedRow-1>-1&&selectedCol-1>-1&&board[selectedRow-1][selectedCol-1]!=null&&board[selectedRow-1][selectedCol-1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
            }
 //white priest           
                        if(pieceType == PieceTypes.BPriest)
            {
//UpRight               
                  
                if(selectedCol+1<8&&selectedRow-1>-1&&board[selectedRow-1][selectedCol+1]==null||selectedCol+1<8&&selectedRow-1>-1&&board[selectedRow-1][selectedCol+1]!=null&&!board[selectedRow-1][selectedCol+1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }

//DownRight
                
                if(selectedCol+1<8&&selectedRow+1<8&&board[selectedRow+1][selectedCol+1]==null||selectedCol+1<8&&selectedRow+1<8&&board[selectedRow+1][selectedCol+1]!=null&&!board[selectedRow+1][selectedCol+1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol+1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                            
                        }
                
//DownLeft                
                   
                if(selectedRow+1<8&&selectedCol-1>-1&&board[selectedRow+1][selectedCol-1]==null||selectedRow+1<8&&selectedCol-1>-1&&board[selectedRow+1][selectedCol-1]!=null&&!board[selectedRow+1][selectedCol-1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow+1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
               
//UpLeft                
                
                if(selectedRow-1>-1&&selectedCol-1>-1&&board[selectedRow-1][selectedCol-1]==null||selectedRow-1>-1&&selectedCol-1>-1&&board[selectedRow-1][selectedCol-1]!=null&&!board[selectedRow-1][selectedCol-1].blackPiece)
                        {
                            g.fillRect(Chess.getX((Chess.getWidth2()/8)*(selectedCol-1)),Chess.getY((Chess.getHeight2()/8)*(selectedRow-1)),Chess.getWidth2()/8 ,Chess.getHeight2()/8);
                        }
            }
        }   
/////// 
    void draw(Graphics2D g,Image image,int xleft,int ytop,int width,int height)
    {
        
        //White Pieces       
        if(pieceType == PieceTypes.WBishop)
        {
            g.drawImage(image,xleft,ytop,width,height,null);
        }
        if(pieceType == PieceTypes.WPawn)
        {
            g.drawImage(image,xleft,ytop,width,height,null);
        }
        if(pieceType == PieceTypes.WRook)
        {
            g.drawImage(image,xleft,ytop,width,height,null);
        }
        if(pieceType == PieceTypes.WKnight)
        {
            g.drawImage(image,xleft,ytop,width,height,null);
        }
        if(pieceType == PieceTypes.WKing)
        {
            g.drawImage(image,xleft,ytop,width,height,null);
        }
        if(pieceType == PieceTypes.WQueen)
        {
            g.drawImage(image,xleft,ytop,width,height,null);
        }
        if(pieceType == PieceTypes.WPriest)
        {
            g.drawImage(image,xleft,ytop,width,height,null);
        }
        
        //Black Pieces
        
        if(pieceType == PieceTypes.BBishop)
        {
            g.drawImage(image,xleft,ytop,width,height,null);
        }
        if(pieceType == PieceTypes.BPawn)
        {
            g.drawImage(image,xleft,ytop,width,height,null);
        }
        if(pieceType == PieceTypes.BRook)
        {
            g.drawImage(image,xleft,ytop,width,height,null);
        }
        if(pieceType == PieceTypes.BKnight)
        {
            g.drawImage(image,xleft,ytop,width,height,null);
        }
        if(pieceType == PieceTypes.BKing)
        {
            g.drawImage(image,xleft,ytop,width,height,null);
        }
        if(pieceType == PieceTypes.BQueen)
        {
            g.drawImage(image,xleft,ytop,width,height,null);
        }
        if(pieceType == PieceTypes.BPriest)
        {
            g.drawImage(image,xleft,ytop,width,height,null);
            //g.fillOval(xleft,ytop,width,height);
            g.setColor(Color.white);
            //g.drawString("Queen", xleft+width/2, ytop+height/2);
        }
    }
    
    boolean move(Piece board[][],int selectedRow, int selectedCol, int currentRow, int currentCol, int turnCount)
    {
        if(board[selectedRow][selectedCol] != null)
        {
            
            if(pieceType == PieceTypes.BPawn)
            {
                    if(firstMove)
                    {
                        if(board[selectedRow+1][selectedCol] == null &&
                           currentRow-2 == selectedRow && currentCol == selectedCol ||
                           currentRow-1 == selectedRow && currentCol == selectedCol &&
                           board[selectedRow+2][selectedCol] == null)
                        {
                            if(currentRow-2 == selectedRow && currentCol == selectedCol)
                            {
                                if(currentCol+1 < 8 && board[currentRow][currentCol+1] != null && board[currentRow][currentCol+1].pieceType  == PieceTypes.WPawn)
                                {
                                    System.out.println("Right SIde");
                                    board[currentRow][currentCol+1].movedtwo = true;
                                    board[currentRow][currentCol+1].pieceTurnCount = turnCount;
                                    board[selectedRow][selectedCol].pieceTurnCount = turnCount;
                                }
                                else if(currentCol-1 > 0 && board[currentRow][currentCol-1] != null && board[currentRow][currentCol-1].pieceType  == PieceTypes.WPawn)
                                {
                                    System.out.println("Left SIde");
                                    board[currentRow][currentCol-1].movedtwo = true;
                                    board[currentRow][currentCol-1].pieceTurnCount = turnCount;
                                    board[selectedRow][selectedCol].pieceTurnCount = turnCount;
                                }
                            }
                            firstMove=false;
                            return(true);
                        }
                        if(board[selectedRow+1][selectedCol] == null && 
                           currentRow-1 == selectedRow && currentCol == selectedCol)
                        {
                            firstMove=false;
                            return(true);
                        }
                        if(board[currentRow][currentCol] != null &&
                           (selectedRow+1 == currentRow && selectedCol+1 == currentCol ||
                            selectedRow+1 == currentRow && selectedCol-1 == currentCol))
                        {
                            firstMove=false;
                            return(true);
                        }   
                    }
                    else
                    {
                        if(selectedRow+1 <=7 && 
                           board[selectedRow+1][selectedCol] == null &&
                           currentRow-1 == selectedRow && currentCol == selectedCol)
                        {
                            return(true);
                        }
                        if(board[currentRow][currentCol] != null &&
                                (currentRow-1 == selectedRow && currentCol-1 == selectedCol ||
                                currentRow-1 == selectedRow && currentCol+1 == selectedCol))
                        {
                            return(true);
                        }
                        if(board[selectedRow][selectedCol].movedtwo)
                        {
                             System.out.println("Empass");
                             if((selectedRow+1 == currentRow && selectedCol-1 == currentCol && board[currentRow-1][currentCol] != null && board[selectedRow][selectedCol-1] != null && board[selectedRow][selectedCol].pieceTurnCount == board[selectedRow][selectedCol-1].pieceTurnCount) || 
                                selectedRow+1 == currentRow && selectedCol+1 == currentCol && board[currentRow-1][currentCol] != null && board[selectedRow][selectedCol+1] != null && board[selectedRow][selectedCol].pieceTurnCount == board[selectedRow][selectedCol+1].pieceTurnCount)
                             {
                                 board[selectedRow][selectedCol].movedtwo = false;
                                 board[currentRow-1][currentCol] = null;
                                 return (true);
                             }
                        }
                    }
            }
            
            if(pieceType == PieceTypes.WPawn)
            {
                if(firstMove)
                {
                    if(board[selectedRow-1][selectedCol] == null &&
                       board[selectedRow-2][selectedCol] == null&&
                       currentRow+2 == selectedRow && currentCol == selectedCol ||
                       currentRow+1 == selectedRow && currentCol == selectedCol &&
                       board[selectedRow-1][selectedCol] == null&&
                       board[selectedRow-2][selectedCol] == null)
                    {
                        if(currentRow+2 == selectedRow && currentCol == selectedCol)
                        {
                            if(currentCol+1 < 8 && board[currentRow][currentCol+1] != null && board[currentRow][currentCol+1].pieceType  == PieceTypes.BPawn)
                            {
                                System.out.println("Right SIde");
                                board[currentRow][currentCol+1].movedtwo = true;
                                board[currentRow][currentCol+1].pieceTurnCount = turnCount;
                                board[selectedRow][selectedCol].pieceTurnCount = turnCount;
                            }
                            else if(currentCol-1 > 0 && board[currentRow][currentCol-1] != null && board[currentRow][currentCol-1].pieceType  == PieceTypes.BPawn)
                            {
                                System.out.println("Left SIde");
                                board[currentRow][currentCol-1].movedtwo = true;
                                board[currentRow][currentCol-1].pieceTurnCount = turnCount;
                                board[selectedRow][selectedCol].pieceTurnCount = turnCount;
                            }
                        }
                        firstMove=false;   
                        return(true);
                    }
                    if(board[selectedRow-1][selectedCol] == null && 
                       currentRow+1 == selectedRow && currentCol == selectedCol)
                        {
                            firstMove=false;
                            return(true);
                        }
                    if(board[currentRow][currentCol] != null &&
                                (currentRow+1 == selectedRow && currentCol+1 == selectedCol ||
                                currentRow+1 == selectedRow && currentCol-1 == selectedCol))
                        {
                            firstMove=false;
                            return(true);
                        }
                }
                else
                {
                   if(selectedRow-1>-1&&board[selectedRow-1][selectedCol] == null &&
                      currentRow+1 == selectedRow && currentCol == selectedCol)
                        {
                            return(true);
                        }
                   else if(board[currentRow][currentCol] != null &&
                           (currentRow+1 == selectedRow && currentCol+1 == selectedCol ||
                            currentRow+1 == selectedRow && currentCol-1 == selectedCol))
                        {
                            return(true);
                        }
                   if(board[selectedRow][selectedCol].movedtwo)
                   {
                        System.out.println("Empass");
                        if((selectedRow-1 == currentRow && selectedCol-1 == currentCol && board[currentRow+1][currentCol] != null && board[selectedRow][selectedCol-1] != null && board[selectedRow][selectedCol].pieceTurnCount == board[selectedRow][selectedCol-1].pieceTurnCount) || 
                           selectedRow-1 == currentRow && selectedCol+1 == currentCol && board[currentRow+1][currentCol] != null && board[selectedRow][selectedCol+1] != null && board[selectedRow][selectedCol].pieceTurnCount == board[selectedRow][selectedCol+1].pieceTurnCount)
                        {
                            board[selectedRow][selectedCol].movedtwo = false;
                            board[currentRow+1][currentCol] = null;
                            return (true);
                        }
                   }
                }
            }
            
            /////
            
            if(pieceType == PieceTypes.BBishop || pieceType == PieceTypes.WBishop)
            {   
                if(currentRow > selectedRow && currentCol > selectedCol)
                {
                    for(int i = 1;selectedRow+i<8 && selectedCol+i<8;i++)
                    {
                        if(board[currentRow][currentCol] != null &&
                           board[selectedRow][selectedCol].blackPiece != 
                           board[currentRow][currentCol].blackPiece &&
                           selectedRow+i == currentRow && selectedCol+i == currentCol)
                        {
                            return(true);
                        }
                        else if(board[selectedRow+i][selectedCol+i] != null)
                        {
                            return(false);
                        }
                        
                        if(selectedRow+i == currentRow && selectedCol+i == currentCol)
                        {
                            return(true);
                        }
                    }
                }
                else if(currentRow < selectedRow && currentCol < selectedCol)
                {
                    for(int i = 1;selectedRow-i>=0 && selectedCol-i>=0;i++)
                    {
                        if(board[currentRow][currentCol] != null &&
                           board[selectedRow][selectedCol].blackPiece != 
                           board[currentRow][currentCol].blackPiece &&
                           selectedRow-i == currentRow && selectedCol-i == currentCol)
                        {
                            return(true);
                        }
                        else if(board[selectedRow-i][selectedCol-i] != null)
                        {
                            return(false);
                        }
                        if(selectedRow-i == currentRow && selectedCol-i == currentCol)
                        {
                            return(true);
                        }
                    }
                }
                else if(currentRow > selectedRow && currentCol < selectedCol)
                {
                    for(int i = 1;selectedRow+i<8 && selectedCol-i>=0;i++)
                    {
                        if(board[currentRow][currentCol] != null &&
                           board[selectedRow][selectedCol].blackPiece != 
                           board[currentRow][currentCol].blackPiece &&
                           selectedRow+i == currentRow && selectedCol-i == currentCol)
                        {
                            return(true);
                        }
                        else if(board[selectedRow+i][selectedCol-i] != null)
                        {
                            return(false);
                        }
                        if(selectedRow+i == currentRow && selectedCol-i == currentCol)
                        {
                            return(true);
                        }
                    }
                }
                else if(currentRow < selectedRow && currentCol > selectedCol)
                {
                    for(int i = 1;selectedRow-i>=0 && selectedCol+i<=7;i++)
                    {
                        if(board[currentRow][currentCol] != null &&
                           board[selectedRow][selectedCol].blackPiece != 
                           board[currentRow][currentCol].blackPiece &&
                           selectedRow-i == currentRow && selectedCol+i == currentCol)
                        {
                            return(true);
                        }
                        else if(board[selectedRow-i][selectedCol+i] != null)
                        {
                            return(false);
                        }
                        if(selectedRow-i == currentRow && selectedCol+i == currentCol)
                        {
                            return(true);
                        }
                    }
                }
            }
            
            if(pieceType == PieceTypes.BRook || pieceType == PieceTypes.WRook)
            {
                if(currentRow > selectedRow)
                {
                    for(int i = 1;selectedRow+i<=7;i++)
                    {
                        if(board[selectedRow][selectedCol] != null &&
                           board[currentRow][currentCol] != null)
                        {
                            if(board[selectedRow][selectedCol].blackPiece != 
                               board[currentRow][currentCol].blackPiece &&
                               selectedRow+i == currentRow && selectedCol == currentCol)
                            {
                                return(true);
                            }
                        }
                        if(board[selectedRow+i][selectedCol] != null)
                        {
                            return(false);
                        }
                        if(selectedRow+i == currentRow && selectedCol == currentCol)
                        {
                            return(true);
                        }
                    }
                    return(true);
                }
                else if(currentRow < selectedRow)
                {
                    for(int i = 1;selectedRow-i>=0;i++)
                    {
                        if(board[currentRow][currentCol] != null &&
                           board[selectedRow][selectedCol] != null)
                        {
                            if(board[selectedRow][selectedCol].blackPiece != 
                               board[currentRow][currentCol].blackPiece &&
                               selectedRow-i == currentRow && selectedCol == currentCol)
                            {
                                return(true);
                            }
                        }
                        if(board[selectedRow-i][selectedCol] != null)
                        {
                            return(false);
                        }
                        if(selectedRow-i == currentRow && selectedCol == currentCol)
                        {
                            return(true);
                        }
                    }
                    return(true);
                }
                else if(currentCol > selectedCol)
                {
                    for(int i = 1;selectedCol+i<=7;i++)
                    {
                        if(board[currentRow][currentCol] != null &&
                           board[selectedRow][selectedCol] != null)
                        {
                            if(board[selectedRow][selectedCol].blackPiece != 
                               board[currentRow][currentCol].blackPiece &&
                               selectedRow == currentRow && selectedCol+i == currentCol)
                            {
                                return(true);
                            }
                        }
                        if(board[selectedRow][selectedCol+i] != null)
                        {
                            return(false);
                        }
                        if(selectedRow == currentRow && selectedCol+i == currentCol)
                        {
                            return(true);
                        }
                    }
                    return(true);
                }
                else if(currentCol < selectedCol)
                {
                    for(int i = 1;selectedCol-i>=0;i++)
                    {
                        if(board[currentRow][currentCol] != null &&
                           board[selectedRow][selectedCol] != null)
                        {
                            if(board[selectedRow][selectedCol].blackPiece != 
                               board[currentRow][currentCol].blackPiece &&
                               selectedRow == currentRow && selectedCol-i == currentCol)
                            {
                                return(true);
                            }
                        }
                        if(board[selectedRow][selectedCol-i] != null)
                        {
                            return(false);
                        }
                        if(selectedRow == currentRow && selectedCol-i == currentCol)
                        {
                            return(true);
                        }
                    }
                    return(true);
                }
            }
            
            if(pieceType == PieceTypes.BKnight || pieceType == PieceTypes.WKnight)
            {
                    if(selectedRow+2 == currentRow && selectedCol+1 == currentCol ||
                       selectedRow+2 == currentRow && selectedCol-1 == currentCol ||
                       selectedRow-2 == currentRow && selectedCol+1 == currentCol ||
                       selectedRow-2 == currentRow && selectedCol-1 == currentCol ||
                       selectedRow-1 == currentRow && selectedCol+2 == currentCol ||
                       selectedRow-1 == currentRow && selectedCol-2 == currentCol ||
                       selectedRow+1 == currentRow && selectedCol+2 == currentCol ||
                       selectedRow+1 == currentRow && selectedCol-2 == currentCol)
                    {
                        return(true);
                    }
            }
            
            if(pieceType == PieceTypes.BQueen || pieceType == PieceTypes.WQueen)
            {
                //Diagonal Movement
                if(currentRow > selectedRow && currentCol > selectedCol)
                {
                    for(int i = 1;selectedRow+i<8 && selectedCol+i<8;i++)
                    {
                        if(board[currentRow][currentCol] != null &&
                           board[selectedRow][selectedCol].blackPiece != 
                           board[currentRow][currentCol].blackPiece &&
                           selectedRow+i == currentRow && selectedCol+i == currentCol)
                        {
                            return(true);
                        }
                        else if(board[selectedRow+i][selectedCol+i] != null)
                        {
                            return(false);
                        }
                        
                        if(selectedRow+i == currentRow && selectedCol+i == currentCol)
                        {
                            return(true);
                        }
                        
                    }
                }
                else if(currentRow < selectedRow && currentCol < selectedCol)
                {
                    for(int i = 1;selectedRow-i>=0 && selectedCol-i>=0;i++)
                    {
                        if(board[currentRow][currentCol] != null &&
                           board[selectedRow][selectedCol].blackPiece != 
                           board[currentRow][currentCol].blackPiece &&
                           selectedRow-i == currentRow && selectedCol-i == currentCol)
                        {
                            return(true);
                        }
                        else if(board[selectedRow-i][selectedCol-i] != null)
                        {
                            return(false);
                        }
                        if(selectedRow-i == currentRow && selectedCol-i == currentCol)
                        {
                            return(true);
                        }
                    }
                }
                else if(currentRow > selectedRow && currentCol < selectedCol)
                {
                    for(int i = 1;selectedRow+i<8 && selectedCol-i>=0;i++)
                    {
                        if(board[currentRow][currentCol] != null &&
                           board[selectedRow][selectedCol].blackPiece != 
                           board[currentRow][currentCol].blackPiece &&
                           selectedRow+i == currentRow && selectedCol-i == currentCol)
                        {
                            return(true);
                        }
                        else if(board[selectedRow+i][selectedCol-i] != null)
                        {
                            return(false);
                        }
                        if(selectedRow+i == currentRow && selectedCol-i == currentCol)
                        {
                            return(true);
                        }
                    }
                }
                else if(currentRow < selectedRow && currentCol > selectedCol)
                {
                    for(int i = 1;selectedRow-i>=0 && selectedCol+i<=7;i++)
                    {
                        if(board[currentRow][currentCol] != null &&
                           board[selectedRow][selectedCol].blackPiece != 
                           board[currentRow][currentCol].blackPiece &&
                           selectedRow-i == currentRow && selectedCol+i == currentCol)
                        {
                            return(true);
                        }
                        else if(board[selectedRow-i][selectedCol+i] != null)
                        {
                            return(false);
                        }
                        if(selectedRow-i == currentRow && selectedCol+i == currentCol)
                        {
                            return(true);
                        }
                    }
                }
                //Line Movement
                if(currentRow > selectedRow)
                {
                    for(int i = 1;selectedRow+i<=7;i++)
                    {
                        if(board[currentRow][currentCol] != null &&
                           board[selectedRow][selectedCol] != null)
                        {
                            if(board[selectedRow][selectedCol].blackPiece != 
                               board[currentRow][currentCol].blackPiece &&
                               selectedRow+i == currentRow && selectedCol == currentCol)
                            {
                                return(true);
                            }
                        }
                        if(board[selectedRow+i][selectedCol] != null)
                        {
                            return(false);
                        }
                        if(selectedRow+i == currentRow && selectedCol == currentCol)
                        {
                            return (true);
                        }
                    }
                }
                else if(currentRow < selectedRow)
                {
                    for(int i = 1;selectedRow-i>=0;i++)
                    {
                        if(board[currentRow][currentCol] != null &&
                           board[selectedRow][selectedCol] != null)
                        {
                            if(board[selectedRow][selectedCol].blackPiece != 
                               board[currentRow][currentCol].blackPiece &&
                               selectedRow-i == currentRow && selectedCol == currentCol)
                            {
                                return(true);
                            }
                        }
                        if(board[selectedRow-i][selectedCol] != null)
                        {
                            return(false);
                        }
                        if(selectedRow-i == currentRow && selectedCol == currentCol)
                        {
                            return (true);
                        }
                    }
                    return(true);
                }
                else if(currentCol > selectedCol)
                {
                    for(int i = 1;selectedCol+i<=7;i++)
                    {
                        if(board[currentRow][currentCol] != null &&
                           board[selectedRow][selectedCol] != null)
                        {
                            if(board[selectedRow][selectedCol].blackPiece != 
                               board[currentRow][currentCol].blackPiece &&
                               selectedRow == currentRow && selectedCol+i == currentCol)
                            {
                                return(true);
                            }
                        }
                        if(board[selectedRow][selectedCol+i] != null)
                        {
                            return(false);
                        }
                        if(selectedRow == currentRow && selectedCol+i == currentCol)
                        {
                            return(true);
                        }
                    }
                    return(true);
                }
                else if(currentCol < selectedCol)
                {
                    for(int i = 1;selectedCol-i>=0;i++)
                    {
                        if(board[currentRow][currentCol] != null &&
                           board[selectedRow][selectedCol] != null)
                        {
                            if(board[selectedRow][selectedCol].blackPiece != 
                               board[currentRow][currentCol].blackPiece &&
                               selectedRow == currentRow && selectedCol-i == currentCol)
                            {
                                return(true);
                            }
                        }
                        if(board[selectedRow][selectedCol-i] != null)
                        {
                            return(false);
                        }
                        if(selectedRow == currentRow && selectedCol-i == currentCol)
                        {
                            return(true);
                        }
                    }
                    return(true);
                }
            }
            if(pieceType == PieceTypes.WKing || pieceType == PieceTypes.BKing)
            {
                if(selectedRow-1 == currentRow && selectedCol == currentCol ||
                   selectedRow+1 == currentRow && selectedCol == currentCol || 
                   selectedRow == currentRow && selectedCol-1 == currentCol ||
                   selectedRow == currentRow && selectedCol+1 == currentCol ||
                   selectedRow-1 == currentRow && selectedCol+1 == currentCol ||
                   selectedRow-1 == currentRow && selectedCol-1 == currentCol ||
                   selectedRow+1 == currentRow && selectedCol+1 == currentCol || 
                   selectedRow+1 == currentRow && selectedCol-1 == currentCol)
                {
                    if(firstMove)
                        firstMove = false;
                    return(true); 
                }
                //Castle Move
                if((currentRow == 7||currentRow == 0) && currentCol ==6  &&
                   board[selectedRow][selectedCol+1]==null&&
                   board[selectedRow][selectedCol+2]==null&&
                   board[selectedRow][selectedCol].firstMove&&
                   board[selectedRow][selectedCol+3].firstMove)
                {
                    board[selectedRow][selectedCol+1] = board[selectedRow][selectedCol+3];
                    board[selectedRow][selectedCol+3] = null;
                    return (true);
                }
                if((currentRow == 7||currentRow == 0) && currentCol == 1 && 
                   board[selectedRow][selectedCol-1]==null&&
                   board[selectedRow][selectedCol-2]==null&&
                   board[selectedRow][selectedCol-3]==null&&
                   board[selectedRow][selectedCol].firstMove&&
                   board[selectedRow][selectedCol-4].firstMove)
                {
                    board[selectedRow][selectedCol-2] = board[selectedRow][selectedCol-4];
                    board[selectedRow][selectedCol-4] = null;
                    return (true);
                }
                
            }
            if(pieceType == PieceTypes.WPriest || pieceType == PieceTypes.BPriest)
            {
                if(selectedRow-1 == currentRow && selectedCol-1 == currentCol ||
                   selectedRow-1 == currentRow && selectedCol+1 == currentCol ||
                   selectedRow+1 == currentRow && selectedCol-1 == currentCol ||     
                   selectedRow+1 == currentRow && selectedCol+1 == currentCol)
                {
                    if(firstMove)
                        firstMove = false;
                    if(board[currentRow][currentCol] != null)
                    {
                        
                        board[currentRow][currentCol].setPieceType(mindControl(board[currentRow][currentCol].pieceType,board[selectedRow][selectedCol].blackPiece));
                        if(mindControlled)
                        {
                            if(board[selectedRow][selectedCol].blackPiece)
                                board[currentRow][currentCol].blackPiece = true;
                            else
                                board[currentRow][currentCol].blackPiece = false;
                        }
                        return(true);
                    }
                    else
                        return(true); 
                }
            }
        }
        return(false);
    }
}
