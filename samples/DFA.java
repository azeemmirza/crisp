package DFA;


public class DFA
{
    public boolean int_Constant(String a)
    {
        int initial = 0;
        int fs = 2;
        int i = 0;
        char[] b = a.toCharArray();
        while (i < b.length)
        {
            initial = tt_int(initial, b[i]);
            i++;
        }
        if (initial == fs)
        {
            return true;
        }
        else
            return false;
    }


    public static int tt_int(int s, char c)
    {
            int[][] tr = { { 1, 2, 3 }, { 3, 2, 3 }, { 3, 2, 3 }, { 3, 3, 3 } };
        if (c == '-' || c == '+'){ return tr[s][0];}
        else if (c >= 48 && c <= 57) return tr[s][1];
        else return tr[s][2];
    }

    public boolean id(String a)
    {
        int initial = 0;
        int fs = 1;
        int i = 0;
        char[] b = a.toCharArray();
        while (i < b.length)
        {
            initial = TT_id(initial, b[i]);
            i++;
        }
        if (initial == fs)
        {
            return true;
        }
        else
            return false;
    }

    public static int TT_id(int s, char c)
    {
            int[][] tr = { { 1, 2, 2 }, { 1, 1, 2 }, { 2, 2, 2 } };
        if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122))
            return tr[s][0];
            else if (c >= 48 && c <= 57)
        return tr[s][1];
            else
        return tr[s][2];
    }

    public boolean float_Constant(String a)
    {
        int len = a.length;
        int cs = 0, fs = 2;
        char[] arr = new char[len];
        arr = a.toCharArray();
        for (int i = 0; i < a.length; i++)
        {
            cs = TT_float(cs, arr[i]);
        }
        if (cs == fs)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static int TT_float(int cs, char ch)
    {
            int[][] arr1 = { { 1, 2, 1 }, { 3, 2, 1 }, { 3, 3, 2 }, { 3, 3, 3 } };
        if (ch == '+' || ch == '-')
        {
            return arr1[cs, 0];
        }
        else if (ch == '.')
        {
            return arr1[cs][1];
        }
        else if ((int)ch >= 48 && (int)ch <= 57)
        {
            return arr1[cs, 2];
        }
        else
        {
            return 3;
        }
    }


    public  boolean string_const(String s)
    {
        int i = 0, cs = 0, fs = 3;
        while (i < s.length)
        {
            cs = trans_string(cs, s[i]);
            i++;
        }
        if (cs == fs)
            return true;
        else
            return false;
    }

    public static int trans_string(int cs, char ch)
    {
            int[][] array = { { 1, 5, 5, 5 }, { 5, 2, 4, 5 }, { 3, 2, 4, 5 }, { 5, 5, 5, 5 }, { 2, 5, 2, 2 }, { 5, 5, 5, 5 } };
        if (ch == '\"')
            return array[cs, 0];
            else if (ch >= 0 && ch <= 255 && ch != '\'' && ch != '\\' && ch != '\"')
        return array[cs, 1];
            else if (ch == '\\')
        return array[cs, 2];
            else if (ch == '\'')
        return array[cs, 2];
            else
        return 5;
    }



    public static boolean boolean_const(String a)
    {
        int initial = 0;
        int fs = 4;
        int i = 0;

        char[] b = a.toCharArray();
        while (i < b.length)
        {
            initial = tt_boolean(initial, b[i]);
            i++;
        }

        if (initial == fs)
            return true;
        else
            return false;
    }

    public static int tt_boolean(int s, char c)
    {
            int[][] d = {{1,8,8,8,5,8,8,8},{8,2,8,8,8,8,8,8},{8,8,3,8,8,8,8,8},
            {8,8,8,4,8,8,8,8},{8,8,8,8,8,8,8,8},{8,8,8,8,8,6,8,8},
            {8,8,8,8,8,8,7,8},{8,8,8,8,8,8,8,3},{8,8,8,8,8,8,8,8}};
        if (c == 't')
            return d[s, 0];
            else if (c == 'r')
        return d[s, 1];
            else if (c == 'u')
        return d[s, 2];
            else if (c == 'e')
        return d[s, 3];
            else if (c == 'f')
        return d[s, 4];
            else if (c == 'a')
        return d[s, 5];
            else if (c == 'l')
        return d[s, 6];
            else if (c == 's')
        return d[s, 7];
            else
        return d[s, 8];
    }

    public  boolean char_const(String a)
    {
        int initial = 0;
        int fs = 3;
        int i = 0;

        char[] b = a.toCharArray();
        while (i < b.length)
        {
            initial = tt_char(initial, b[i]);
            i++;
        }
        if (initial == fs)
            return true;
        else
            return false;
    }

    public static int tt_char(int s, char c)
    {
            int[][] a = { { 1, 5, 5 }, { 5, 2, 4 }, { 3, 5, 5 }, { 5, 5, 5 }, { 5, 2, 5 }, { 5, 5, 5 } };

        if (c == '\'') return a[s, 0];
            else if (c == 92)
        return a[s, 2];
            else
        return a[s, 1];
    }
}
