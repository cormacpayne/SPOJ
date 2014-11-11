import java.util.*;
import java.io.*;

class SUBSUMS {

	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		int n = in.nextInt();
		int a = in.nextInt();
		int b = in.nextInt();
		int total = 1 << n;
		int firstHalf, secondHalf;

		if (n % 2 == 0)	firstHalf = secondHalf = n / 2;
		else { firstHalf = n / 2; secondHalf = 1 + n / 2; }

		int firstSize = 1 << firstHalf;
		int secondSize = 1 << secondHalf;

		long[] first = new long[firstSize];
		long[] second = new long[secondSize];

		long[] values = new long[n];

		for (int i = 0; i < n; i++) values[i] = in.nextLong();

		for (int j = 0; j < firstSize; j++) {
			long sum = 0;
			for (int k = 0; k < firstHalf; k++) if ((j & (1 << k)) != 0) sum += values[k];
			first[j] = sum;
		}

		Arrays.sort(first);

		for (int j = 1; j < secondSize; j++) {
			long sum = 0;
			for (int k = 0; k < secondHalf; k++) if ((j & (1 << k)) != 0) sum += values[k + firstHalf];
			second[j] = sum;
		}

		Arrays.sort(second);

		long count = 0;
		for (long f : first) {
			int low = 0;
			int high = secondSize - 1;

			while (high > low) {
				int mid = (low + high) / 2;
				if (f + second[mid] >= a) high = mid;
				else low = mid + 1;
			}
			int lowIndex = high;

			low = 0;
			high = secondSize - 1;

			while (high > low) {
				int mid = (low + high + 1) / 2;
				if (f + second[mid] <= b) low = mid;
				else high = mid - 1;
			}
			int highIndex = low;
			if (a <= f + second[lowIndex] && b >= f + second[highIndex]) count += highIndex - lowIndex + 1;
		}

		System.out.println(count);
	}
}

class Parser
{
   final private int BUFFER_SIZE = 1 << 16;

   private DataInputStream din;
   private byte[] buffer;
   private int bufferPointer, bytesRead;

   public Parser(InputStream in)
   {
      din = new DataInputStream(in);
      buffer = new byte[BUFFER_SIZE];
      bufferPointer = bytesRead = 0;
   }

   public long nextLong() throws Exception
   {
      long ret = 0;
      byte c = read();
      while (c <= ' ') c = read();
      boolean neg = c == '-';
      if (neg) c = read();
      do
      {
         ret = ret * 10 + c - '0';
         c = read();
      } while (c > ' ');
      if (neg) return -ret;
      return ret;
   }
   
   //reads in the next string
   public String next() throws Exception
   {
      StringBuilder ret =  new StringBuilder();
      byte c = read();
      while (c <= ' ') c = read();
      do
      {
         ret = ret.append((char)c);
         c = read();
      } while (c > ' ');
      return ret.toString();
   }

   public int nextInt() throws Exception
   {
      int ret = 0;
      byte c = read();
      while (c <= ' ') c = read();
      boolean neg = c == '-';
      if (neg) c = read();
      do
      {
         ret = ret * 10 + c - '0';
         c = read();
      } while (c > ' ');
      if (neg) return -ret;
      return ret;
   }
   
   private void fillBuffer() throws Exception
   {
      bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
      if (bytesRead == -1) buffer[0] = -1;
   }

   private byte read() throws Exception
   {
      if (bufferPointer == bytesRead) fillBuffer();
      return buffer[bufferPointer++];
   }
}
