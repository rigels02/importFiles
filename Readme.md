# Console Java application importFiles

Imports sdf files and generates output file in required format.
Uses **chemaxon.formats.MolImporter** class
 
[https://docs.chemaxon.com/display/docs/Reading+Molecules+From+a+File](https://docs.chemaxon.com/display/docs/Reading+Molecules+From+a+File) 

## How does it work?

The main parser class is Parser_v2.java.

It uses MolImport() to get properties from input file.

Using 'CATALOG_NUMBER' property it gets defined FileType.

The FileType is used to get proper Parser instance (Parser_v2Tx) from FileTypeParserFactoryV2.java.

The rest of task is done by parser Parser_v2Tx.(Parser_v2T1, Parser_v2T2, Parser_v2T3, Parser_v2T4, Parser_v2T5,)

The result of list of records (Rec) is passed to OutputFormater.outPrint() to get output report.
