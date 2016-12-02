public class Utils {
    protected final static long longmask=0x00000000ffffffffL;
    protected final static int intmask=0xFFFFFFFF;
    /**
     * ïðåîáðàçîâûâàåò ñòðîêó â ìàññèâ áàéò 
     * @param input ñòðîêà ââîäà
     * @return ìàññèâ áàéò
     */
    public byte[] toByteArray(String input)
    {
        byte[] bytes = new byte[input.length()];

        for (int i = 0; i != bytes.length; i++)
        {
            bytes[i] = (byte)input.charAt(i);
        }

        return bytes;
    }
    /**
     * ïðåîáðàçîâàòü â long èç ìàññèâà 8 áàéò, óêàçàííûõ â intOff
     * @param in ìàññèâ äëÿ ïðåîáðàçîâàíèÿ
     * @param inOff ñ êàêîãî ìåñòà ìàññèâà íà÷àòü ïðåîáðàç-å
     * @return ÷èñëî äëèíîé long
     */
    public long bytesTolong(byte[]  in,  int  inOff)    {
        long l=0L;
        int i=in.length-inOff;
        i=i>8?8:i;
        switch (i){
            case 8:	l+=((long)in[inOff + 7] << 56)	& 0xff00000000000000L;
            case 7:	l+=((long)in[inOff + 6] << 48) 	& 0x00ff000000000000L;
            case 6:	l+=((long)in[inOff + 5] << 40) 	& 0x0000ff0000000000L;
            case 5:	l+=((long)in[inOff + 4] << 32) 	& 0x000000ff00000000L;
            case 4:	l+=((long)in[inOff + 3] << 24) 	& 0x00000000ff000000L;
            case 3:	l+=((long)in[inOff + 2] << 16) 	& 0x0000000000ff0000L;
            case 2:	l+=((long)in[inOff + 1] << 8) 	& 0x000000000000ff00L;
            case 1:	l+=(long) in[inOff    ] 		& 0x00000000000000ffL;
        }
        return l;
    }
    /**
     * long to array of bytes
     * @param num ÷èñëî òèïà long äëÿ ïðåîáðàçîâàíèÿ
     * @param out ìàññèâ áàéò ñ ðåç-òîì
     * @param outOff ìåñòî â ìàññèâå êóäà çàíåñòè ðåç-ò
     */
    public void longTobytes(long num, byte[]  out,  int outOff) {
        int i=out.length-outOff;
        i=i>8?8:i;
        switch (i){
            case 8:	out[outOff + 7] = (byte)(num >>> 56);
            case 7:	out[outOff + 6] = (byte)(num >>> 48);
            case 6:	out[outOff + 5] = (byte)(num >>> 40);
            case 5:	out[outOff + 4] = (byte)(num >>> 32);
            case 4:	out[outOff + 3] = (byte)(num >>> 24);
            case 3:	out[outOff + 2] = (byte)(num >>> 16);
            case 2:	out[outOff + 1] = (byte)(num >>> 8);
            case 1:	out[outOff    ] = (byte)num;
        }

    }

    /**
     * âûáðàòü èç ìàññèâà áàéò íåñê-êî ýëåìåíòîâ è ïðåîáðàçîâàòü èõ â ìàññèâ òèïà short
     * @param S èñõîäíûé ìàññèâ
     * @param wS ðåç-ùèé ìàññèâ
     */
    public void cpyBytesToShort(byte[] S, short[] wS) {
        for(int i=0; i<S.length/2; i++) {
            wS[i] = (short)(((S[i*2+1]<<8)&0xFF00)|(S[i*2]&0xFF));
        }
    }
    /**
     * âûáðàòü èç ìàññèâà short íåñê-êî ýëåìåíòîâ è ïðåîáðàçîâàòü èõ â ìàññèâ òèïà áàéò
     * @param wS èñõîäíûé ìàññèâ
     * @param S ðåçóëüòèð.ìàññèâ
     */
    public void cpyShortToBytes(short[] wS, byte[] S) {
        for(int i=0; i<S.length/2; i++)  {
            S[i*2 + 1] = (byte)(wS[i] >> 8);
            S[i*2] = (byte)wS[i];
        }
    }
    /**
     * ìàññèâ áàéò â òèï integer
     * @param in ìàññèâ áàéò
     * @param inOff íà÷àëüíûé ïîçèöèÿ â ìàññèâå äëÿ ïðåîáðàçîâ.
     * @return ÷èñëî òèïà int
     */
    public int bytesToint(byte[]  in,  int  inOff)    {
        return  ((in[inOff + 3] << 24) & 0xff000000) + ((in[inOff + 2] << 16) & 0xff0000) +
                ((in[inOff + 1] << 8) & 0xff00) + (in[inOff] & 0xff);

    }
    /**
     * integer â ìàññèâ áàéò (int To Little Endian)
     * @param num ÷èñëî òèïà int äëÿ ïðåîáðàç.â ìàññèâ áàéò
     * @param out ìàññèâ áàéò ñ ðåç-òîì
     * @param outOff ïîçèö.â ìàññèâå êóäà çàïèñàòü ðåç-ò
     */
    public void intTobytes(int     num, byte[]  out,  int     outOff) {
        out[outOff + 3] = (byte)(num >>> 24);
        out[outOff + 2] = (byte)(num >>> 16);
        out[outOff + 1] = (byte)(num >>> 8);
        out[outOff] =     (byte)num;

    }
    /**
     * long â ìàññèâ áàéò in little endian
     * @param n ÷èñëî äëÿ çàïèñè â ìàññèâ
     * @param bs ìàññèâ áàéò â êîòîðûé çàïèñûâàåì ÷èñëî n
     * @param off ìåñòî íà÷àëà ìàññèâà ñ êîòîðîãî çàïèñûâàåì ÷èñëî n
     */
    public void longToLittleEndian(long n, byte[] bs, int off)
    {
        intTobytes((int)(n & 0xffffffffL), bs, off);
        intTobytes((int)(n >>> 32), bs, off + 4);
    }

    /**
     * âìåñòî Hotspot-ðåàëèçàöèè System.arraycopy èñïîëüçóåì ñâîþ ïëàòôîðìîíåçàâèñèìóþ
     * äëÿ ìàññèâà òèïà short[]
     * @param from ìàññèâ èñòî÷íèê
     * @param startfrom ìåñòî íà÷àëà êîïèðîâàíèÿ èç èñòî÷íèêà
     * @param to ìàññèâ ïðèåìíèê
     * @param startto ìåñòî íà÷àëà çàïèñè â ïðèåìíèêå
     * @param len äëèíà êîïèðóåìûõ äàííûõ
     */
    public void array2array(short[] from,int startfrom, short[] to, int startto, int len) {
        //long start, end;
        //start = System.nanoTime();
        int predel=len+startfrom;
        for(; startfrom < predel; startfrom++) {
            to[startto++] = from[startfrom];
        }
        //end = System.nanoTime();
        //System.out.println("Âðåìÿ âûïîëíåíèÿ: " + (end - start));
    }
    /**
     * âìåñòî Hotspot-ðåàëèçàöèè System.arraycopy èñïîëüçóåì ñâîþ ïëàòôîðìîíåçàâèñèìóþ
     * äëÿ ìàññèâà òèïà int[]
     * @param from ìàññèâ èñòî÷íèê
     * @param startfrom ìåñòî íà÷àëà êîïèðîâàíèÿ èç èñòî÷íèêà
     * @param to ìàññèâ ïðèåìíèê
     * @param startto ìåñòî íà÷àëà çàïèñè â ïðèåìíèêå
     * @param len äëèíà êîïèðóåìûõ äàííûõ
     */
    public void array2array(int[] from,int startfrom, int[] to, int startto, int len) {
        //long start, end;
        //start = System.nanoTime();
        int predel=len+startfrom;
        for(; startfrom < predel; startfrom++) {
            to[startto++] = from[startfrom];
        }
        //end = System.nanoTime();
        //System.out.println("Âðåìÿ âûïîëíåíèÿ: " + (end - start));
    }
    /**
     * âìåñòî Hotspot-ðåàëèçàöèè System.arraycopy èñïîëüçóåì ñâîþ ïëàòôîðìîíåçàâèñèìóþ
     * äëÿ ìàññèâà òèïà byte[]
     * @param from ìàññèâ èñòî÷íèê
     * @param startfrom ìåñòî íà÷àëà êîïèðîâàíèÿ èç èñòî÷íèêà
     * @param to ìàññèâ ïðèåìíèê
     * @param startto ìåñòî íà÷àëà çàïèñè â ïðèåìíèêå
     * @param len äëèíà êîïèðóåìûõ äàííûõ
     */
    public void array2array(byte[] from,int startfrom, byte[] to, int startto, int len) {
        //long start, end;
        //start = System.nanoTime();
        int predel=len+startfrom;
        for(; startfrom < predel; startfrom++) {
            to[startto++] = from[startfrom];
        }
        //end = System.nanoTime();
        //System.out.println("Âðåìÿ âûïîëíåíèÿ: " + (end - start));
    }
    /**
     * ñëîæåíèå áåççíàêîâûõ ÷èñåë
     * @param a ïåðâûé ïàðàìåòð ñóììàòîðà
     * @param b âòîðîé ïàðàìåòð ñóììàòîðà
     * @return ðåç-ò âûïîëåíèÿ ñóììàòîðà
     */
    public long addUInt32(long a, long b) {
        long c = a + b;
        return (long)((long)c & (long)intmask);
    }
    /**
     * îáúåäèíèòü 2 âõîäíûõ int äî long
     * @param N1 ïåðâûé ïàðàìåòð
     * @param N2 âòîðîé ïàðàìåòð
     * @return ðåç-ò âûïîëåíèÿ
     */
    public long extension(int N1, int N2) {
        return ((long)N1<<32)+((long)N2&longmask);
    }
    /**
     * âû÷èñëåíèå ðàçðÿäíîñòè ÷èñëà  äëÿ int
     * åñëè âîçâðàùàåìîå ÷èñëî áîëüøå 0 òî â ñòàðøèõ ðàçðÿäàõ (31-28) åñòü çíà÷àùèé áèò
     * @param Ask ÷èñëî òèïà int äëÿ àíàëèçà
     * @return áèòîâûé ðàçðÿä ÷èñëà
     */
    public int Razrad(int Ask) {
        return Ask&0xF0000000;
    }
    /**
     * îáìåí ì/ä äâóìÿ ïåðåìåí.int
     * ðåç-òîì áóäåò çíà÷åíèå x â y, à çíà÷åíèå y â x
     * @param x 1 èñõîäíàÿ ïåðåìåííàÿ
     * @param y 2 èñõîäíàÿ ïåðåìåííàÿ
     */
    public void xorSwap(int x, int y) {
        x ^= (y ^= (x ^= y));
    }

}