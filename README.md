# Java
Index and Concodance
. Index for a book
An index lists every word that occurs in the document, and for each word it gives the page
number in the document where the word occurs. Write a program that can create an index
for a document. The document should be read from an input file, and the index data should
be written to an output file.
ii. Concordance for a book
A concordance lists every word that occurs in the document, and for each word it gives
the line number of every line in the document where the word occurs. The only real
difference is that the integers in a concordance are line numbers rather than page numbers.
Write a program that can create a concordance. The document should be read from an
input file, and the concordance data should be written to an output file.
The input and output files should be selected by the user when the program is run. As you
read the file, you want to take each word that you encounter and add it to the concordance
along with the current line number. Keeping track of the line numbers is one of the trickiest
parts of the problem. In an input file, the end of each line in the file is marked by the
newline character, ’\n’. Every time you encounter this character, you have to add one to
the line number. Also, you will need to detect the end of the file. Because it is so common,
don’t include the word “the” in your concordance. Also, do not include words that have
length less than 3 characters
