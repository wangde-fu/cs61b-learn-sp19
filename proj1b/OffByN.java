public class OffByN implements CharacterComparator{
    private int N;
    public OffByN(int N) {this.N = N;}
    @Override
    public boolean equalChars(char x, char y){
        /*if (Math.abs(x-y)==1) {
            return true;
        }
        return false;*/
        return Math.abs(x-y)==N;
    }
}
