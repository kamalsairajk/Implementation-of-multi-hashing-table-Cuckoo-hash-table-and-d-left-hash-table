import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class mainrun {
    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the hash table dor which the demo should run");
        System.out.println("1.MultiHashTable\n2.CuckooHashTable\n3.DleftHashTable");
        int option=sc.nextInt();
        FileWriter output = new FileWriter("output" + option + ".txt");

        if(option==1){
            System.out.println("Enter the number of entries");
            int numentries=sc.nextInt();
            System.out.println("Enter the number of hashes");
            int numhash=sc.nextInt();
            MultiHashTable ht = new MultiHashTable(numentries, numhash);
            for (int i = 0; i < 1000; i++) {
                int flowId = Math.abs(rand.nextInt());
                ht.insert(flowId);
            }
            output.write("Number of flows in the hashtable = " + ht.numentries+"\n");
            System.out.println("Number of flows in the hashtable = " + ht.numentries);

            for (int i = 0; i < ht.size; i++) {
                if (ht.table[i]==null){
                    output.write((0)+"\n");
                    System.out.println(0);
                }
                else{
                    output.write((ht.table[i].flowid)+"\n");
                    System.out.println((ht.table[i].flowid));}
            }
            }
        else if (option==2) {
            System.out.println("Enter the number of entries");
            int numentries=sc.nextInt();
            System.out.println("Enter the number of hashes");
            int numhash=sc.nextInt();
            System.out.println("Enter the number of cuckoo steps");
            int steps=sc.nextInt();
            CuckooHashTable ht = new CuckooHashTable(numentries, numhash, steps);
            for (int i = 0; i < 1000; i++) {
                int flowId = Math.abs(rand.nextInt());
                ht.insert(flowId);
            }
            output.write("Number of flows in the hashtable = " + ht.numentries+"\n");
            System.out.println("Number of flows in the hashtable = " + ht.numentries);

            for (int i = 0; i < ht.size; i++) {
                if (ht.table[i]==null){
                    output.write((0)+"\n");
                    System.out.println(0);
                }
                else{
                    output.write((ht.table[i].flowid)+"\n");
                System.out.println((ht.table[i].flowid));}
            }
        }
        else if (option==3) {
            System.out.println("Enter the number of entries");
            int numentries=sc.nextInt();
            System.out.println("Enter the number of segments(hashes)");
            int numhash=sc.nextInt();
            DleftHashTable ht = new DleftHashTable(1000, 4);
            for (int i = 0; i < 1000; i++) {
                int flowId = Math.abs(rand.nextInt());
                ht.insert(flowId);
            }
            output.write("Number of flows in the hashtable = " + ht.numentries+"\n");
            System.out.println("Number of flows in the hashtable = " + ht.numentries);

            for (int i = 0; i < ht.numhash; i++) {
                for(int j=0;j<ht.size;j++){
                if (ht.segmentarr[i][j]==null){
                    output.write(0+"\n");
                    System.out.println(0);

                }
                else{
                    output.write(ht.segmentarr[i][j].flowid+"\n");
                    System.out.println(ht.segmentarr[i][j].flowid);

                }}
            }
        }
        output.close();

}}
class flowcounter{
    int flowid;
    int counter;
    flowcounter(int flowid){
        this.flowid=flowid;
    }
    flowcounter(int flowid,int counter){
        this.flowid=flowid;
        this.counter=counter;
    }
}

class MultiHashTable{
    int numentries;
    int numhash;
    int []s;
    flowcounter []table;
    int size;
    Random rand=new Random();
    MultiHashTable(int size, int numhash){
        this.size=size;
        this.numhash=numhash;
        this.s=new int[numhash];
        this.table=new flowcounter[size];
        Arrays.fill(table,null);
        hashfunctions();
    }
    public boolean insert(int flowid){
        for(int i=0;i<numhash;i++){
            int xor=flowid^s[i];
            if (table[xor%size]==null) {
                table[xor % size]= new flowcounter(flowid);
                numentries++;
                return true;
            }
        }
        return false;
    }
    public void hashfunctions(){
        for(int i=0;i<this.numhash;i++){
            s[i]=Math.abs(rand.nextInt());
        }
    }
}
class CuckooHashTable{
    int numentries;
    int numhash;
    int []s;
    flowcounter []table;
    int size;
    int steps;
    Random rand=new Random();
    CuckooHashTable(int size, int numhash,int steps){
        this.size=size;
        this.numhash=numhash;
        this.steps=steps;
        this.s=new int[numhash];
        this.table=new flowcounter[size];
        Arrays.fill(table,null);
        hashfunctions();
    }
    public void hashfunctions(){
        for(int i=0;i<this.numhash;i++){
            s[i]=Math.abs(rand.nextInt());
        }
    }
    public boolean insert(int flowid) {
        for (int i = 0; i < numhash; i++) {
            int xor = flowid ^ s[i];
            if (table[xor%size]==null) {
                table[xor % size]= new flowcounter(flowid);
                numentries++;
                return true;
            }
        }
        for (int i = 0; i < numhash; i++) {
            int xor = flowid ^ s[i];
            int alrpreent=table[xor%size].flowid;
            if (move(alrpreent,steps)) {
                table[xor % size]= new flowcounter(flowid);
                numentries++;
                return true;
            }
        }
        return false;

    }
    public boolean move(int flowid,int steps) {
        for (int i = 0; i < numhash; i++) {
            int xor = flowid ^ s[i];
            if (table[xor%size]==null) {
                table[xor % size]= new flowcounter(flowid);
                return true;
            }
        }
        for (int i = 0; i < numhash; i++) {
            int xor = flowid ^ s[i];
            if (table[xor % size] == null&& steps>=1 && move(flowid, steps - 1)) {
                table[xor % size]= new flowcounter(flowid);
                return true;
            }
        }
        return false;
    }
}
class DleftHashTable{
        int numentries;
        int numhash;
        int []s;
        int size;
        Random rand=new Random();
        flowcounter segmentarr[][];

        DleftHashTable(int size, int numhash){
            this.numhash=numhash;
            this.size=size/numhash;
            this.s=new int[numhash];
            this.segmentarr=new flowcounter[numhash][this.size];
            for(int i=0;i<this.numhash;i++){
                Arrays.fill(segmentarr[i],null);
            }
            hashfunctions();
        }
        public void hashfunctions(){
            for(int i=0;i<this.numhash;i++){
                s[i]=Math.abs(rand.nextInt());
            }
        }
        public boolean insert(int flowid){
            for (int i = 0; i < numhash; i++) {
                int xor = flowid ^ s[i];
                if (segmentarr[i][xor % this.size] == null) {
                    segmentarr[i][xor % this.size] =new flowcounter(flowid);
                    numentries++;
                    return true;
                }
            }
            return false;
        }
}

