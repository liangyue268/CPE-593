/**
 * Created by yue on 5/3/2015.
 */
public class KMP {
    public static int kmp(String search, String target){
        int[] offset = new int[256];
        int m = target.length();
        for(int i=0; i<256; i++){
            offset[i] = m;
        }
        for(int i=0; i<target.length(); i++){
            offset[target.charAt(i)] = m - (i+1);
        }

        /*print out jump table
        for(int i=0; i<256; i++){
            if(offset[i] != m){
                System.out.println((char)i + ":" + offset[i]);
            }
        }*/

        int pos = m - 1;
        while(pos < search.length()){
            int jump = offset[search.charAt(pos)];
            if(jump == 0){
                //possible found
                if(match(search, target, pos)){
                    return pos- (target.length() - 1);
                }
                pos++;//if pos has no change and do not match the target, then it will be stuck.
            }
            else{
                pos += jump;
            }
        }
        return -1;
    }

    public static boolean match(String search, String target, int lastIdx){
        for(int i=target.length() - 1,j=lastIdx; i >= 0; i--,j--){
            if(search.charAt(j) != target.charAt(i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        String search = "testtestettesting";
        String target = "testing";
        System.out.print(kmp(search, target));
    }
}