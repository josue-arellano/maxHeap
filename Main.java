/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxheap;

import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        String menu = "Please select how to test the program:\n"
                + "(1) 20 sets of 100 randomly generated integers\n"
                + "(2) Fixed integer values 1-100\n"
                + "Enter choice: ";
        Scanner kb = new Scanner(System.in);
        int choice = 9;
        String ans = "";
        boolean test = true;
        while (test)
        {
            System.out.print(menu);
            ans = kb.nextLine();
            Scanner ansReader = new Scanner(ans);
            if (ansReader.hasNextInt())
            {
                choice = ansReader.nextInt();
            }
            if (choice != 1 && choice != 2)
            {
                System.out.println("Wrong input!");
            } else
            {
                test = false;
            }
        }
        System.out.println();
        if (choice == 1)
        {
            int swapSum = 0;
            int optimalSwapSum = 0;
            for (int i = 0; i < 20; i++)
            {
                MaxHeap<Integer> firstHeap = new MaxHeap<Integer>(100);
                Random rand = new Random();
                Integer[] nums = new Integer[100];
                for (int j = 0; j < 100; j++)
                {
                    int newNum = rand.nextInt(1000);
                    while(contains(nums, j, newNum))
                    {
                        newNum = rand.nextInt();
                    }
                    nums[j] = newNum;
                    firstHeap.add(newNum);
                }
                MaxHeap<Integer> secondHeap = new MaxHeap<Integer>(nums);
                swapSum += firstHeap.numberOfSwaps();
                optimalSwapSum += secondHeap.numberOfSwaps();
                nums = null;
            }
            System.out.println("Average swaps for series of insertions: "
                               + swapSum / 20);
            System.out.println("Average swaps for optimal insertions: "
                               + optimalSwapSum / 20);
        } else
        {
            System.out.println("2");
        }
        
    }
    
    public static boolean contains(Integer[] arr, int index, Integer val)
    {
        for(int i = 0; i < index + 1; i++)
        {
            if(val == arr[i])
                return true;
        }
        return false;
    }
}
