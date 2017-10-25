using System;
using System.Collections.Generic;
using System.Linq;

using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace lexicalanalyzer
{
    class FileRead{
        string lex = System.IO.File.ReadAllText("D:/test.txt");
    }
    class L_A
    {
        string code;
        int i, j = 0;
        char[] temp = new char[127];
        List<tokens> tokenarray = new List<tokens>();
        int codelength;
        int lineno = 1;
        public L_A(string code)
        {
            this.code = code;
            codelength = code.Length - 1;
        }
        public List<tokens> generate()
        {
            for (; i <= codelength; i++)
            {
                temp[j] = code[i];

                if (code[i] == '"')
                {
                    if (j > 0)
                    {
                        i--; j--;
                        tokenarray.Add(new tokens(new string(temp, 0, j + 1), new string(temp, 0, j + 1), lineno));
                        j = 0;
                    }
                    else
                    {
                        string literal = stringliteral();
                        tokenarray.Add(new tokens(literal, lineno));
                        j = 0;
                    }
                }

                else if (code[i] == '\'')
                {
                    if (i < codelength)
                        temp[++j] = code[++i];
                    if (code[i] == '\\' && i < codelength - 1)
                    {
                        temp[++j] = code[++i];
                    }
                    if (i < codelength && code[i + 1] != '\n')
                        temp[++j] = code[++i];

                    tokenarray.Add(new tokens(new string(temp, 0, j + 1), lineno));
                    j = 0;
                }

                else if (isopertor(code[i]))
                {
                    if (j > 0)
                    {
                        i--; j--;
                        tokenarray.Add(new tokens(new string(temp, 0, j + 1), lineno));
                        j = 0;
                    }
                    else { addoperatortoken(); j = 0; }
                }
                else if (code[i] == '.')
                {

                    if (i < codelength - 1 && code[i + 1] >= '0' && code[i + 1] <= '9' && (temp[0] == '.' || (temp[0] >= '0' && temp[0] <= '9')) || ((i > 0) && (temp[i - 1] >= '0' && temp[i - 1] <= '9')))
                    {
                        temp[++j] = code[++i];
                        String floatConstant = readFloatConstant();
                        tokenarray.Add(new tokens(floatConstant, lineno));
                        j = 0;
                    }
                    else
                    {
                        if (j > 0)
                        {
                            i--; j--;
                            tokenarray.Add(new tokens(new string(temp, 0, j + 1), lineno));
                            j = 0;
                        }
                        else
                        {
                            tokenarray.Add(new tokens((new string(temp, 0, j + 1)), new string(temp, 0, j + 1), lineno));
                            j = 0;
                        }

                    }

                }
                else if (IsWordBreaker(code[i]))
                {
                    if (j > 0)
                    {
                        i--; j--;
                        tokenarray.Add(new tokens(new string(temp, 0, j + 1), lineno));
                        j = 0;
                    }
                    else
                    {
                        tokenarray.Add(new tokens((new string(temp, 0, j + 1)), new string(temp, 0, j + 1), lineno));
                        j = 0;
                    }
                }
                else if (code[i] == '\n')
                {
                    lineno++;
                    if (j > 0)
                    {
                        tokenarray.Add(new tokens(new string(temp, 0, j + 1), lineno));
                        j = 0;
                    }
                }
                else
                    if (code[i] == ' ' || code[i] == '\t' || code[i] == '\r')
                {

                    if (j != 0)
                    {
                        tokenarray.Add(new tokens(new string(temp, 0, j), lineno));
                        j = 0;
                    }

                }
                else if (code[i] == '~' && code[i + 1] == '~')
                {
                    if (j > 0)
                    {
                        tokenarray.Add(new tokens(new string(temp, 0, j), lineno));
                        j = 0;
                    }

                    //deal with the comment
                    comments();

                }
                else { j++; }

            }
            if (j != 0)
            {
                tokenarray.Add(new tokens(new string(temp, 0, j), lineno));
            }
            return tokenarray;
        }
        public void addoperatortoken()
        {
            string classname = null;
            if (i < codelength)
            {
                if ((code[i] == '+' && code[i + 1] == '+') || (code[i] == '-' && code[i + 1] == '-'))
                { classname = "INC_DEC";
                    temp[++j] = code[++i];

                   }
                else if ((code[i] == '&' && code[i + 1] == '&'))
                { classname = "AND"; temp[++j] = code[++i]; }
                else if ((code[i] == '|' && code[i + 1] == '|'))
                { classname = "OR"; temp[++j] = code[++i]; }

                else if ((code[i] == '%' || code[i] == '*' || code[i] == '/' || code[i] == '+' || code[i] == '-') && (code[i + 1] == '='))
                { classname = "ASSIG_OP"; temp[++j] = code[++i]; }
                else if ((code[i] == '<' || code[i] == '>' || code[i] == '=' || code[i] == '!') && (code[i + 1] == '='))
                { classname = "REL_OP"; temp[++j] = code[++i]; }
            }
            if (classname == null)
            {
                if (code[i] == '=') { classname = "EQUAL"; }
                else if (code[i] == '+' || code[i] == '-') { classname = "ADD_SUB"; }
                else if (code[i] == '*' || code[i] == '/') { classname = "MUL_DIV"; }
                else if (code[i] == '>' || code[i] == '<') { classname = "REL_Opp"; }

            }
            tokenarray.Add(new tokens(classname, new string(temp, 0, j + 1), lineno));

        }
        public bool isopertor(char c)
        {
            return (c == '+' || c == '-' || c == '*' || c == '/' || c == '>' || c == '<' || c == '&' || c == '=' || c == '!');
        }
        public bool IsWordBreaker(char c)
        {
            return (c == '(' || c == ')' || c == '[' || c == ',' || c == ']' || c == '{' || c == '}' || c == ';' || c == ':');
        }
        public string readFloatConstant()
        {
            while (i <= codelength && !IsWordBreaker(code[i]) && !isopertor(code[i]) && code[i] != '"' && code[i] != '\'' && code[i] != '#' && code[i] != '.' && code[i] != '\r' && code[i] != '\n' && code[i] != ' ')
            {
                temp[j] = code[i];
                i++; j++;
            }
            i--;
            return new string(temp, 0, j);
        }
        public string stringliteral()
        {
            if (i < codelength)
                temp[++j] = code[++i];
            while (temp[j] != '"' && i < codelength && temp[j] != '\n')
            {
                if (i < codelength)
                    temp[++j] = code[++i];
                while (temp[j] != '"' && i < codelength && temp[j] != '\n')
                {

                    if (i < codelength && temp[j] == '\\')
                    {
                        while (i < codelength - 1 && temp[j] == '\\')
                        {
                            {
                                temp[++j] = code[++i];
                                if (i < codelength)
                                    temp[++j] = code[++i];
                            }
                            /* if (i<EndCode-1&&temp[j] == '\\')
                             {
                                 temp[++j] = code[++i];
                                 temp[++j] = code[++i];
                             }*/
                        }

                    }
                    /*
                while (temp[j] != '"' && i < EndCode )
                {

                    if (i < EndCode && temp[j] == '\\')
                    {
                        while (i < EndCode - 1 && temp[j] == '\\')
                        {
                            {
                                temp[++j] = code[++i];
                                if (i < EndCode)
                                    temp[++j] = code[++i];
                            }
                            if (temp[j] == '\n')
                                line_no++;
                            /* if (i<EndCode-1&&temp[j] == '\\')
                             {
                                 temp[++j] = code[++i];
                                 temp[++j] = code[++i];
                             }/
                        }

                    }*/
                    else if (i < codelength)
                    {
                        i++; j++;
                        temp[j] = code[i];
                    }
                    else if (temp[j] == '\n')
                        lineno++;

                }


            }
            if (temp[j] == '\n')
                i--;
            return new string(temp, 0, j + 1);
        }

        public void comments()
        {
            if (code[i] == '~' && code[i + 1] == '~')
            {
                i = i + 2;
            }
            while (code[i] != '~' && code[i + 1] != '~')
            {
                if (code[i] == '\n')
                {
                    lineno++;
                    break;
                }
                i++;
            }
            i = i + 2;
            j = 0;
        }
    }
    class tokens
    {
        string classname;
        string value;
        int lineno;
        public tokens(string classname, string value, int lineno)
        {
            this.classname = classname;
            this.value = value;
            this.lineno = lineno;
        }
        public tokens(string value, int lineno)
        {
            this.classname = gettokenclass(value);
            this.value = value;
            this.lineno = lineno;
        }
        public string gettokenclass(string value)
        {
            string classname = null;
            string[] keywords = { "break", "case", "if", "else", "body", "void", "until", "in", "switch" };
            string[] data_types = { "schar", "fdata", "sdata" };

            if (value[0] == '"')
            {

                if (validiatebydfa(value, 0))
                {
                    classname = "string constant";

                }
                else
                    classname = "error";

            }
            else if (value[0] == '\'')
            {
                if (validiatebydfa(value, 1))
                {
                    classname = "'char constant";
                }
                else
                {
                    classname = "error";
                }
            }
            else if (value.Contains("."))
            {
                if (validiatebydfa(value, 3))
                {
                    classname = "FLOAT_CONST";
                }
                else
                {
                    classname = "ERORR";

                }
            }
            else if (value[0] >= '0' && value[0] <= '9')
            {
                if (validiatebydfa(value, 4))
                {
                    classname = "INTEGER_CONST";
                }
                else
                {
                    classname = "ERORR";

                }
            }

            else
            {
                foreach (string x in keywords)
                {
                    if (value.Equals(x))
                    {
                        classname = value.ToUpper();
                    }
                }
                if (classname == null)
                    foreach (string x in data_types)
                    {
                        if (value.Equals(x))
                        {
                            classname = "DATA_TYPE";
                        }
                    }
                if (classname == null)
                    if (value[0] >= 'a' && value[0] <= 'z' || value[0] >= 'A' && value[0] <= 'Z' )
                    {
                        if (validiatebydfa(value, 2))
                        {
                            classname = "initilizer";
                        }
                        else classname = "Error";

                    }

            }

            return classname;
        }

        public bool validiatebydfa(string input, int condition)
        {
            int currentstate = 0;
            int finalstate = 1;
            int ii = 0;
            char[] c = input.ToCharArray();
            switch (condition)
            {
                case 0:
                    finalstate = 3;
                    while (ii != c.Length)
                    {
                        currentstate = transitionstringconstant(currentstate, c[ii]);
                        ii++;
                    }
                    break;
                case 1:
                    finalstate = 3;
                    while (ii != c.Length)
                    {
                        currentstate = TransitionOfCharacterConstant(currentstate, c[ii]);
                        ii++;
                    }
                    break;
                case 2:
                    finalstate = 1;
                    while (ii != c.Length)
                    {
                        currentstate = tranistion_initilizer(currentstate, c[ii]);
                        ii++;
                    }
                    break;
                case 3:
                    finalstate = 2;

                    while (ii != c.Length)
                    {
                        currentstate = TransitionOffloatConstant(currentstate, c[ii]);
                        ii++;
                    }
                    break;
                case 4:
                    while (ii != c.Length)
                    {
                        currentstate = TransitionOfIntegerConstant(currentstate, c[ii]);
                        ii++;
                    }
                    break;
                default:
                    break;
            }
            return (currentstate == finalstate);

        }
        public int transitionstringconstant(int state, int character)
        {
            int[,] TransitionTable ={{1,4,4}, //0      //"=0 \n =1 anchar=2
                                    {3,4,2}, //1
                                    {3,4,2}, //2
                                    {4,4,4},//3
                                    {4,4,4}//4
                                    };
            if (character == '"')
            {
                return TransitionTable[state, 0];
            }
            else if ((character >= ' ' && character <= '~' && character != '\\' && character != '\"') || character == ' ')
                return TransitionTable[state, 2];
            else //dead state 4
                return TransitionTable[state, 1];

        }

        int TransitionOfCharacterConstant(int state, char character)
        {
            int[,] TransitionTable = new int[,] { { 1, 5, 5, 5 }, { 5, 2, 4, 2 }, { 3, 5, 5, 5 }, { 5, 5, 5, 5 }, { 2, 5, 2, 2 }, { 5, 5, 5, 5 } };
            if (character == '\'')
                return TransitionTable[state, 0];
            else if (character == 't' || character == 'n' || character == 'b' || character == '"' || character == 'r' || character == 'v' || character == '0')
                return TransitionTable[state, 3];
            else if ((character >= ' ' && character <= '~' && character != '\\' && character != '\''))
                return TransitionTable[state, 1];
            else if (character == '\\')
                return TransitionTable[state, 2];
            else
                return 5;
        }

        public int tranistion_initilizer(int state, char character)
        {
            int[,] transtiontable ={
                                          {1},
                                          {1}
                                      };
            if (character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z' || character=='_')
            { return transtiontable[state, 0]; }
            else return 5;
        }

        int TransitionOffloatConstant(int state, char character)
        {
            int[,] TransitionTable = new int[,] { { 0, 1 }, { 2, 3 }, { 2, 3 }, { 3, 3 } };
            if (character == '.')
                return TransitionTable[state, 1];
            else if ((character >= '0' && character <= '9'))
                return TransitionTable[state, 0];
            else
                return 3;
        }
        int TransitionOfIntegerConstant(int state, char character)
        {
            int[,] TransitionTable = new int[,] { { 1, 2 }, { 1, 3 }, { 1, 3 }, { 3, 3 } };
            if (character == '+' || character == '-')
                return TransitionTable[state, 1];
            else if ((character >= '0' && character <= '9'))
                return TransitionTable[state, 0];
            else
                return 3;
        }

        public string getToken()
        {
            return "( " + this.classname + " , " + this.value + " , " + lineno + " )";
        }
        public string getClass()
        {
            return this.classname;
        }
        public int getLineNo()
        {
            return this.lineno;
        }
    }
    class Program
    {
        static void Main(string[] args)
        {
            List<tokens> tokenarray;
            string text = System.IO.File.ReadAllText(@"D:\Development\test.txt");
            L_A la = new L_A(text);
            tokenarray = la.generate();
            int size = tokenarray.Count();
            string temp = null;
            for (int i = 0; i < size; i++)
            {
                tokenarray[i].getLineNo();
                tokenarray[i].getClass();
                tokenarray[i].GetType();
                Console.WriteLine(tokenarray[i].getToken());
                temp += tokenarray[i].getToken() + "\t \n";
            }

            System.IO.File.WriteAllText(@"D:\Development\files.txt", temp);
            Console.ReadLine();
        }
    }
}

