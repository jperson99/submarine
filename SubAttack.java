import java.util.*;
//asdfasdf
public class SubAttack {
    static int bombX, bombY;
    static final int worldWidth = 300;
    static final int worldHeight = 300;
    static int subWidth, subHeight;
    static int subX, subY;
    static final int blastRadius = 30;
    static int indirectHits = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            subWidth = (int) (Math.random() * 31 + 40); 
            subHeight = (int) (Math.random() * 11 + 10); 

            subX = random.nextInt(worldWidth - subWidth + 1);
            subY = random.nextInt(worldHeight - subHeight + 1);
            
            System.out.println("Submarine has been spotted!");
            System.out.println("You have 5 charges.");
            
            int bombs = 5;
            indirectHits = 0;
            while(bombs > 0){
                System.out.println("Enter the x position of the depth charge: ");
                bombX = Integer.parseInt(scan.next());
                
                System.out.print("Enter the y position of the depth charge: ");
                bombY = Integer.parseInt(scan.next());
                
                if(bombX - blastRadius < 0 || bombX + blastRadius > worldWidth || bombY - blastRadius < 0 || bombY + blastRadius > worldHeight){
                    System.out.println("*Error! The bomb blast is outside the world border.");
                    System.out.println("Re-enter the coordinates of the depth charge: ");
                }else if(indirectHit()){
                    indirectHits++;
                    System.out.println("** Indirect Hit! **");
                    if(indirectHits >= 2){
                        System.out.println("Indirect hit again! The submarine has been destroyed!");
                        break;
                    }
                }else if(directHit(blastRadius)){
                    System.out.println("** Direct Hit! **");
                    System.out.println("The Submarine has been destroyed!");
                    break;
                }else{
                    System.out.println("Missed. Try Again");
                }
                
                bombs--;
                
                if(bombs == 0){
                    System.out.println("Out of bombs! The submarine has escaped");
                    break;
                }
            }
            String playAgain;
            System.out.println("Another submarine has been spotted! Would you like to engage? (y/n)");
            playAgain = scan.next();
            if(playAgain.equals("n")){
                System.out.println("Thanks for playing");
                break;
            }
        }
    }

    public static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((double) (x1 - x2), 2) + Math.pow((double) (y1 - y2), 2));
    }
    
    public static boolean indirectHit() {
        return directHit(50) && !directHit(blastRadius);
    }
    
    public static boolean directHit(int radius) {
        if (distance(bombX, bombY, subX, subY) <= radius) {
            return true;
        }
        if (distance(bombX, bombY, subX + subWidth, subY) <= radius) {
            return true;
        }
        if (distance(bombX, bombY, subX, subY + subHeight) <= radius) {
            return true;
        }
        if (distance(bombX, bombY, subX + subWidth, subY + subHeight) <= radius) {
            return true;
        }

        if (bombX >= subX && bombX <= subX + subWidth) {
            if (Math.abs(bombY - subY) <= radius) {
                return true;
            }
            if (Math.abs(bombY - (subY + subHeight)) <= radius) {
                return true;
            }
        }
        if (bombY >= subY && bombY <= subY + subHeight) {
            if (Math.abs(bombX - subX) <= radius) {
                return true;
            }
            if (Math.abs(bombX - (subX + subWidth)) <= radius) {
                return true;
            }
        }
        if (bombX >= subX && bombX <= subX + subWidth && bombY >= subY && bombY <= subY + subHeight) {
            return true;
        }
        return false;
    }
}

