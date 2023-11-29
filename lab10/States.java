public class States {
    private int n; 
    private int m;
    private String[] sts;
    
    public States(int capacity) {
        this.m = capacity;
        this.n = 0;
        sts = new String[m];
    }
    
    public int add(String str) {
        int i;
        for(i = hash(str); sts[i] != null; i = (i + 1) % m) {
            if(sts[i].equals(str)) {
                return i;
            }
        }
        sts[i] = str;
        n++;
        return i;
    }
    
    public String get(int ind) {
        return sts[ind];
    }

    public int hash(String s) {
        int h = (s.hashCode() & 0x7fffffff) % m;
        return h;
    }
    
}
