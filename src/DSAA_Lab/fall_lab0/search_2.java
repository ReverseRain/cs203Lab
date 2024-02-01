package DSAA_Lab.fall_lab0;

public class search_2 {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int[]array=new int[(int)Math.pow(10,5)+1];
        for (int i = 0; i < n; i++) {
            array[in.nextInt()]++;
        }
        int T= in.nextInt();
        for (int i = 0; i < T; i++) {
            if (array[in.nextInt()]!=0){
                out.println("yes");
            }else {
                out.println("no");
            }
        }
    out.close();//close要放在最后
         }
}

