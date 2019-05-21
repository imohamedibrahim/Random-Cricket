import java.util.*;
class cricket{
    public HashMap<Integer,String> countries=new HashMap<Integer,String>();
    public static HashSet<Integer> playerMap=new HashSet<Integer>();
    public static HashSet<Integer> playerMap1=new HashSet<Integer>();
    public static HashMap<String,Integer> bowler=new HashMap<String,Integer>();
    static String numAsString[]={"<--FOUR-->","<--SIX-->"};
    private int overs,nation1,nation2;
    String players[][]={{"Rohit Sharma ", "KL Rahul", "Virat Kholi", "Manish Pandey","Dinesh Karthik", "MS Dhoni (wk)(C)", "Hardik Pandya", "Washington Sundar", "Yuzvendra Chahal", "Kuldeep Yadav", "Deepak Hooda", "Jasprit Bumrah", "Mohammed Siraj", "Basil Thampi", "Jaydev Unadkat"},{"Steven Smith(c)", "David Warner", "George Bailey", "Scott Boland", "Aaron Finch", "John Hastings", "Travis Head", "Usman Khawaja", "Mitchell Marsh", "Joe Mennie", "Chris Tremain", "Matthew Wade", "Daniel Worrall","Adam Zampa", "James Faulkner", "Shaun Marsh"},{"Faf du Plessis(c)", "Kyle Abbott", "Hashim Amla", "Farhaan Behardien", "Quinton de Kock", "Jean-Paul Duminy", "Imran Tahir", "David Miller", "Wayne Parnell", "Aaron Phangiso", "Andile Phehlukwayo", "Kagiso Rabada", "Rilee Rossouw", "Tabraiz Shamsi", "Dale Steyn"},{"Kane Williamson(c)", "Tom Latham", "Todd Astle", "Trent Boult", "Lockie Ferguson", "Matt Henry", "Adam Milne", "Colin Munro", "Henry Nicholls", "Tim Southee", "Ross Taylor", "George Worker", "Doug Bracewell","Brendon Mccullum","Corey Anderson"},{"Jason Holder(c)","Jason Mohammed", "Ronsford Beaton", "Shannon Gabriel", "Chris Gayle", "Kyle Hope", "Shai Hope", "Evin Lewis", "Nikita Miller", "Ashley Nurse", "Rovman Powell", "Kesrick Williams", "Sheldon Cottrell", "Chadwick Walton", "Shimron Hetmyer"},{"Upul Tharanga", "Thisara Perera (c)","Nuwan Pradeep", "Niroshan Dickwella (wk)", "Kusal Perera", "Danushka Gunathilaka", "Dushmantha Chameera"," Dasun Shanaka", "Akila Dananjaya", "Sadeera Samarawickrama", "Asela Gunaratne","Sachith Pathirana", "Vishwa Fernando", "Chaturanga de Silva","Muthiahya Muralitharan"}};
    static String nameSelected[][]=new String[2][11];
    cricket(){
      
          countries.put(1,"India");
          countries.put(2,"Australia");
          countries.put(3,"South Africa");
          countries.put(4,"New Zealand");
          countries.put(5,"West Indies");
          countries.put(6,"Srilanka");
        // System.out.println(countries.get(1));
          
      }
    cricket(int overs,int nation1,int nation2){
          
          countries.put(1,"India");
          countries.put(2,"Australia");
          countries.put(3,"South Africa");
          countries.put(4,"New Zealand");
          countries.put(5,"West Indies");
          countries.put(6,"Srilanka");
          this.overs=overs;
          this.nation1=nation1;
          this.nation2=nation2;
    }
    public int[] startInings(int k,int flag){
         int a[]=new int[2];
         int run=0,wicket=0;
         int target=k;
         Random p=new Random();
         for(int i=1;i<overs+1;i++){
            displayBowler(p,flag);
            int status[]=startOver(i,wicket,target,flag,run);
            run=run+status[0];
            wicket=status[1];
            showOverStatus(run,status[0],wicket,i);
            if(wicket>9){
                a[0]=run+status[0];a[1]=10;
                return a;
            }
            if(flag==1){
                if(k-run>0&&i+1!=overs+1){
                target=k-run;
                displayTargetNeeded(target,i,overs);
            }else if(k-run<0){
                a[0]=run;
                a[1]=wicket;
                return a;
             }
            }
           
        }
        
        a[0]=run;
        a[1]=wicket;
        return a;
       
    }
    public void displayBowler(Random r,int innings){
        if(innings==1){
            innings=0;
        }else{
            innings=1;
        }
        System.out.println("The BOWLER :"+nameSelected[innings][Math.abs(r.nextInt()%11)]);
    }
    public static void displayTargetNeeded(int target,int i,int over){
        System.out.println("Need "+target+" in "+ (6*(over-i))+" Balls ");
    }
    public static void showOverStatus(int run,int runForTheOver,int wicket,int i){
            System.out.println("_________________________________________________________________");
            System.out.println(" runs this over : "+runForTheOver);
            System.out.println(" Over : "+i+"||"+" Run/Wicket : "+run+"/"+wicket);
            System.out.println("_________________________________________________________________");
            
    }
    public static int[] startOver(int i,int wicket,int target,int flag,int run){
            int runThisOver=0;
            Random r1=new Random();
            int notConsidered=0;
            int status=0;
            int cons=0;
            Boolean freeHit=false;
            for(int j=1;j<7;j++){
                if(flag==1&&target-runThisOver<=0){
                    int a[]={runThisOver,wicket};
                    return a;
                }
                System.out.print(i+"."+j+" : ");
                boolean checkForNoBall=isThisNoBall(r1);
                if(freeHit){
                    System.out.print("<FREE HIT>:");
                }
                if(checkForNoBall){
                    j--;
                    
                }
                int[] isItOut=isThatOut(r1,checkForNoBall,freeHit,flag,wicket);
                if(isItOut[0]==-1) { 
                     status=displayBoundry(r1);
                }else{
                  //  runThisOver=runThisOver+isItOut[0];
                    //wicket=wicket++;
                    status=isItOut[0];
                    wicket++;
                    
                    if(wicket>9){
                     int a[]={runThisOver,wicket};
                     return a;
                    }
                nextToCrease(wicket,flag);
                }
                    //continue;
                
                if(checkForNoBall){
                    runThisOver++;
                    freeHit=true;
                    
                }else{
                    freeHit=false;
                }
                runThisOver=runThisOver+status;
               }
               
                //System.out.println("Runs this  Over : "+runThisOver);
                int a[]={runThisOver,wicket};
                return a;
    }
    public static int displayBoundry(Random r1){
                     int status=runForABall(r1);
                     System.out.print(status);
                     if(status%6==0&&status!=0){
                         System.out.print(numAsString[1]);
                     }else if(status%4==0&&status!=0){
                         System.out.print(numAsString[0]);
                     }
                    System.out.println();
                    return status;
    }
    public static void nextToCrease(int wicket,int flag){
               System.out.println("NEXT TO CREASE:"+nameSelected[flag][wicket]);
    }
    public static boolean isThisNoBall(Random r1){
        int r=r1.nextInt();
        if(r%20==0){
            System.out.print("NO  BALL :");
            return true;
        }
        return false;
    }
    public static int[] isThatOut(Random r1,boolean noBall,boolean freeHit,int innings,int number){//looking whether there is wicket loss
        int r=r1.nextInt();
        r=Math.abs(r)%20;
        int runs=0,wicket=0;
        switch(r){
            case 1: System.out.println("Caught");
                    if(!noBall||!freeHit){
                        ///System.out.print(noBall);
                        wicket++;
                    }else{
                        runs=r%4;
                    }
                    break;
            case 2: System.out.println("Bowled");
                    if(!noBall){
                        wicket++;
                    }
                    
                    break;
            case 3: System.out.println("Run Out + runned :"+r%4);
                    runs=r%4;
                    break;
            default:runs=-1;
                    break;
        }
        int a[]={runs,wicket};
        return a;
         
    }
    public static int runForABall(Random r1){                 //calculating the run for a ball
        int r=r1.nextInt();
        r=Math.abs(r);
        r=r%20;
        if(r%2==0&&r>15){
            return 1;
        }else if(r%3==0&&r>15){
            return 2;
        }else if(r==7){
            return 3;
        }else if(r==11||r==5){
            return 6;
        }else if(r==13||r==17){
            return 4;
        }
        return 0;
        
    }          
    public void showCountries(){                              //displaying countries list
        System.out.println("Choose Two countries to start the match");
        Set s=countries.entrySet();
        Iterator i=s.iterator();
        while(i.hasNext()){
            Map.Entry country=(Map.Entry)i.next();
            System.out.println(country.getKey()+" "+country.getValue());
        }
        
    }
    public void showResult(int country1,int country2,int wicket){  //displaying which team won
        if(country1>country2){
            System.out.println(countries.get(nation1)+" won By "+(country1-country2)+" runs");
        }else if(country1<country2){
            System.out.println(countries.get(nation2)+" won by "+(10-wicket)+" wickets");
        }else{
            System.out.println("Draw");
        }
    }
    public void showCountryNumber(int choice){                      //Displaying the Specific country
        System.out.println(countries.get(choice));
    }
    public void displayPlayer(int nationCode){                      //Displaying player
        for(int i=1;i<=15;i++){
            System.out.println(i+" "+players[nationCode-1][i-1]);
        }
    }
    public void assignPlayerOfCountries(int playerNumber,int innings,int country,int i){ //Assigning and displaying Selected Players
        nameSelected[innings][i]=players[country-1][playerNumber-1];
        if(i==10){
            System.out.println("PLAYING X1 "+Arrays.toString(nameSelected[innings]));
        }
    }
    public static void printStarLine(){
        System.out.println("*******************************************************************************************");
    }
   
       
}

 public class Match{
    
    public static void main(String[] args) {
        try{
        Scanner s=new Scanner(System.in);
        cricket cri=new cricket();
        cri.showCountries();
        int choosenCountry1;
        int choosenCountry2;
        while(true){
            choosenCountry1=s.nextInt();
            choosenCountry2=s.nextInt();
        if(choosenCountry1!=choosenCountry2&&choosenCountry1<=cri.countries.size()&&choosenCountry2<=cri.countries.size()){
            System.out.print("Enter Number of Overs");
            break;
        }else if(choosenCountry1>cri.countries.size()||choosenCountry2>cri.countries.size()){
            System.out.println("Enter Valid Input");
        }else{
            System.out.println("Select Different Countries");
        }
        }
        int numOfOvers=s.nextInt();
        System.out.print(" : "+numOfOvers);
        System.out.println();
        cricket battle=new cricket(numOfOvers,choosenCountry1,choosenCountry2);
        System.out.println(battle.countries.get(choosenCountry1)+" Squad");
        battle.displayPlayer(choosenCountry1);
        System.out.println("Choose Players for "+battle.countries.get(choosenCountry1));
        int i=0;
        while(i<11){
            int getPlayerNumber=s.nextInt();
            if(getPlayerNumber>15||battle.playerMap.contains(getPlayerNumber)){
                System.out.println("Select valid Player");
                i--;
                continue;
            }else{
                if(!battle.playerMap.contains(getPlayerNumber)){
                battle.assignPlayerOfCountries(getPlayerNumber,0,choosenCountry1,i);
                battle.playerMap.add(getPlayerNumber);
                }
            i++;
        }
        }
        i=0;
        System.out.println(battle.countries.get(choosenCountry2)+" Squad");
        battle.displayPlayer(choosenCountry2);
        System.out.println("Choose Players for "+battle.countries.get(choosenCountry2));
        
        while(i<11){
            int getPlayerNumber=s.nextInt();
            if(getPlayerNumber>15||battle.playerMap1.contains(getPlayerNumber)){
                System.out.println("Select valid Player");
                i--;
                continue;
            }else{
                if(!battle.playerMap1.contains(getPlayerNumber)){
                battle.assignPlayerOfCountries(getPlayerNumber,1,choosenCountry2,i);
                battle.playerMap1.add(getPlayerNumber);
                }}
            i++;
        }
        
             
        int[] runOfCountry1=battle.startInings(0,0);
        battle.printStarLine();
        System.out.println("END OF FIRST INNINGS");
        battle.printStarLine();
        System.out.println(battle.countries.get(choosenCountry1)+" : "+runOfCountry1[0]+"/"+runOfCountry1[1]);
        System.out.println("TARGET : "+(runOfCountry1[0]+1)+" FOR "+(battle.countries.get(choosenCountry2)).toUpperCase());
        
        int[] runOfCountry2=battle.startInings(runOfCountry1[0],1);
        battle.showResult(runOfCountry1[0],runOfCountry2[0],runOfCountry2[1]);
        }catch(Exception e){
            System.out.println("ENTER VALID INPUT");
        }
        }
}
