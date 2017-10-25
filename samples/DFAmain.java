using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DFA
{
    class Program
    {
        static void Main(string[] args)
        {
            DFAs dfachk = new DFAs();
            int i = 0;
            while (i == 0)
            {
                Console.WriteLine("\nCheck Again:");
                string cases = Convert.ToString(Console.ReadLine());
                switch (cases)
                {
                    case "int":
                        {
                            string chk = Convert.ToString(Console.ReadLine());

                            if (dfachk.int_Constant(chk) == true)
                            {
                                Console.WriteLine("True");
                            }
                            else
                                Console.WriteLine("False");
                            break;
                        }
                    case "string":
                        {
                            string chk = Convert.ToString(Console.ReadLine());

                            if (dfachk.string_const(chk) == true)
                            {
                                Console.WriteLine("True");
                            }
                            else
                                Console.WriteLine("False");
                            break;
                        }
                    case "float":
                        {
                            string chk = Convert.ToString(Console.ReadLine());

                            if (dfachk.float_Constant(chk) == true)
                            {
                                Console.WriteLine("True");
                            }
                            else
                                Console.WriteLine("False");
                            break;
                        }
                    case "char":
                        {
                            string chk = Convert.ToString(Console.ReadLine());

                            if (dfachk.char_const(chk) == true)
                            {
                                Console.WriteLine("True");
                            }
                            else
                                Console.WriteLine("False");
                            break;
                        }
                    case "id":
                        {
                            string chk = Convert.ToString(Console.ReadLine());

                            if (dfachk.id(chk) == true)
                            {
                                Console.WriteLine("True");
                            }
                            else
                                Console.WriteLine("False");
                            break;
                        }
                    default:
                        break;

                }
                
            }

            Console.ReadLine();
        }
    }
}
