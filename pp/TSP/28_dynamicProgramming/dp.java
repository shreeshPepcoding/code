import java.util.*;

public class dp {

    // stock buy and sell, one transaction
    private static void stockBuySell1(int[] stocks) {
        int oprofit = 0; // overall profit
        int min = Integer.MAX_VALUE;
        for(int d = 0; d < stocks.length; d++) {
            int price = stocks[d];
            min = Math.min(min, price);
            oprofit = Math.max(oprofit, price - min);
        }
        System.out.println(oprofit);
    }

    // stock buy and sell, infinite transaction
    private static void stockBuySell2(int[] price) {
        int bd = 0;
        int sd = 0;
        int profit = 0;

        for(int i = 1; i < price.length; i++) {
            if(price[i] > price[i - 1]) {
                sd++;
            } else {
                profit += price[sd] - price[bd];
                // reset bd and sd
                bd = i;
                sd = i;
            }
        }
        // is peak is present in price int end of days
        profit += price[sd] - price[bd];
        System.out.println(profit);
    }

    // stock buy and sell with transaction fees, infinite transaction
    private static void stockBuySell3(int[] price, int fees) {
        int pibt = -price[0]; // pibt -> profit if buy today
        int pist = 0; // initially 0, pist-> profit if sell today
        for(int i = 1; i < price.length; i++) {
            int new_pibt = Math.max(pibt, pist - price[i]);
            int new_pist = Math.max(pist, price[i] + pibt - fees);
            pibt = new_pibt;
            pist = new_pist;
        }
        System.out.println(pist);
    }

    // stock buy and sell with cooldown, infinite transaction
    private static void stockBuySell4(int[] price) {
        int pibt = -price[0]; // pibt -> profit if buy today
        int pist = 0; // initially 0, pist-> profit if sell today
        int pwcd = 0; // pwcd -> profit with cooldown
        for(int i = 1; i < price.length; i++) {
            int new_pibt = Math.max(pibt, pwcd - price[i]);
            pwcd = pist;
            int new_pist = Math.max(pist, price[i] + pibt);
            pibt = new_pibt;
            pist = new_pist;
        }
        System.out.println(pist);
    }

    // stock buy and sell, two transaction
    private static void stockBuySell5(int[] price) {
        int[] profitFromSellToday = new int[price.length];
        int profit = 0;

        // fill from left to right -> profit if we sell stock today
        int min = price[0];
        for(int i = 1; i < price.length; i++) {
            min = Math.min(min, price[i]);
            profitFromSellToday[i] = Math.max(profitFromSellToday[i - 1], price[i] - min);
        }

        // fill from right to lef in same array
        int max = Integer.MIN_VALUE;
        for(int i = price.length - 1; i >= 0; i--) {
            max = Math.max(max, price[i]);
            int profitFromBuyToday = max - price[i];
            profit = Math.max(profit, profitFromSellToday[i] + profitFromBuyToday);
        }
        System.out.println(profit);
    }

    // stock buy and sell, K transaction
    private static void stockBuySell6(int[] price, int k) {
        
    }


    public static void main(String[] args) {

    }
}