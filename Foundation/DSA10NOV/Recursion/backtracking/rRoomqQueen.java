public class rRoomqQueen {

    public static int rRoomQqueenPerm(boolean[] rooms, int qpsf, int tq, String asf) {
        // base case
        if(qpsf == tq) {
            // all queens are settled
            System.out.println(asf + ".");
            return 1;
        }

        int count = 0;
        for(int r = 0; r < rooms.length; r++) {
            if(rooms[r] == false) {
                rooms[r] = true; // placing qpsf queen at r room
                count += rRoomQqueenPerm(rooms, qpsf + 1, tq, asf + "R" + r + "Q" + qpsf + ", ");
                rooms[r] = false;
            }
        }
        return count;
    }

    // lro => last room occupied
    public static int rRoomQqueenComb(boolean[] rooms, int qpsf, int tq, int lro, String asf) {
        // base case
        if(qpsf == tq) {
            // all queens are settled
            System.out.println(asf + ".");
            return 1;
        }

        int count = 0;
        for(int r = lro; r < rooms.length; r++) {
            rooms[r] = true; // placing qpsf queen at r room
            count += rRoomQqueenComb(rooms, qpsf + 1, tq, r + 1, asf + "R" + r + "Q" + qpsf + ", ");
            rooms[r] = false;
        }
        return count;
    }

    public static void solve() {
        int r = 3;
        boolean[] rooms = new boolean[r];
        int q = 2;
        // System.out.println(rRoomQqueenPerm(rooms, 0, q, ""));
        System.out.println(rRoomQqueenComb(rooms, 0, q, 0, ""));
    }

    public static void main(String[] args) {
        solve();
    }
}