import java.util.Random;
import java.util.Scanner;
class Tic
{
    String P1Name,P2name;
    private char[] play={'1','2','3','4','5','6','7','8','9'};

    Tic(String name1,String name2)
    {
        P1Name=name1;
        P2name=name2;
    }

    public void display()
    {
        System.out.println(play[0]+" | "+play[1]+" | "+play[2]);
        System.out.println("---------");
        System.out.println(play[3]+" | "+play[4]+" | "+play[5]);
        System.out.println("---------");
        System.out.println(play[6]+" | "+play[7]+" | "+play[8]);
    }

    public void putX(int pos)
    {
        play[pos-1]='X';
    }
    public void putO(int pos)
    {
        play[pos-1]='O';
    }

    public boolean validNumber(int pos)
    {
        if(pos<0 || pos>9 || play[pos-1]=='O' || play[pos-1]=='X')
        {   
            Scanner sc=new Scanner(System.in);
            System.err.println("Enter a Valid Number!\nEnter a  number which are there in the above box");
            System.out.println("Press enter to continue");
            sc.nextLine();
            clearScr();
            return false;
        }
        return true;
    }
    public byte XorO(int i)
    {
        if(play[i]=='X')
            return 1;
        else
            return 2;
    }
    public byte checkWin()
    {//check for all 3 fields has same value ,then check its X or O;
        //Horizontal
        boolean k=false;
        for(int i=0;i<9;i=i+3)
        { 
            if(play[i]==play[i+1] && play[i]==play[i+2])
                return XorO(i);
        }
        // vertical
        for(int i=0;i<3;i++)
        { 
            if(play[i]==play[i+3] && play[i]==play[i+6])
                return XorO(i);
        }
        //diagnals
        if(play[0]==play[4] && play[0]==play[8])
            return XorO(0);
        if(play[2]==play[4] && play[2]==play[6])
            return XorO(2);
        return 0;
    }
    public void clearScr()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
 
}
public class App 
{   
    public static void main(String[] args) 
    {
        
        System.out.print("Enter palyer 1 name:");
        Scanner sc= new Scanner(System.in);
        String P1,p2;
        P1=sc.nextLine();
        System.out.print("Enter palyer 2 name:");
        p2=sc.nextLine();
        Tic p1=new Tic(P1,p2);
        System.out.println(p1.P1Name+" is X\n"+p1.P2name+" is O\nPress enter to continue");
        sc.nextLine();
        p1.clearScr();
        p1.display();
        System.out.println("Enter the number which is present in the position for your selection ");
        System.out.println("Press enter to continue");
        sc.nextLine();
        p1.clearScr();
        byte win=0;
        for(int i=1;i<=9;i++)
        {
            int pos;
            if(i%2!=0)
            {
                p1.display();
                System.out.print(p1.P1Name+" enter your posistion:");
                pos=sc.nextInt();
                boolean valid= p1.validNumber(pos);
                if(valid==false)
                {
                    i--;
                    continue;
                }
                p1.putX(pos);
                p1.clearScr();
            }
            else
            {
                p1.display();
                System.out.print(p1.P2name+" enter your posistion:");
                pos=sc.nextInt();
                boolean valid= p1.validNumber(pos);
                if(valid==false)
                {
                    i--;
                    continue;
                }
                p1.putO(pos);
                p1.clearScr();
            }
            if(i>=5)
            {           
                win=p1.checkWin();
                if(win!=0)
                    break;
            }    
        }
        p1.clearScr();
        p1.display();
        if(win==0)
        {
            System.out.println("Game over!\nIt's a draw.Try again");
        }
        else if (win==1)
        {
            System.out.println("Game Over\nCongratulations "+p1.P1Name+" is the Winner");   
        }
        else
            System.out.println("Congratulations "+p1.P2name+" is the Winner");
        // Random r=new Random();
        // for(int i=0;i<10;i++)
        // {   int k=r.nextInt(0, 10);
        //     System.out.println(k);
        // }
    }  
}
