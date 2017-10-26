CC		= gcc
PROG	= testParser 

$(PROG): main.o scanner.o parser.o
	$(CC) -o $(PROG) main.o scanner.o parser.o

main.o : main.c token.h scanner.h parser.h
	$(CC) -c main.c

scanner.o : scanner.c token.h scanner.h symdef.h
	$(CC) -c scanner.c

parser.o : parser.c parser.h token.h 
	$(CC) -c parser.c

.PHONY: clean

clean:
	/usr/bin/rm -rf core $(PROG) *.o
