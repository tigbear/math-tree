#Shared advisor with Richard Feynman
#file://users/Users/tanya/CrawlResults/31332.html
#python record.py --in_file /Users/tanya/CrawlResults/31332.html --out_file /Users/tanya/Record/31332.json
#https://genealogy.math.ndsu.nodak.edu/id.php?id=31332

# Self
#python record.py --in_file /Users/tanya/CrawlResults/169822.html --out_file /Users/tanya/Record/169822.json
#https://genealogy.math.ndsu.nodak.edu/id.php?id=169822

import sys, getopt, os
from BeautifulSoup import BeautifulSoup

def make_record(txt, id):
    soup = BeautifulSoup(txt)
    record = dict()
    record["id"] = id
    record["name"] = soup.h2.contents[0].strip().rstrip()
    return record

def main(argv):
    in_file = ''
    out_file = ''
    try:
        opts, args = getopt.getopt(argv,"hi:o:",["in_file=","out_file="])
    except getopt.GetoptError:
        print 'record.py -i <in_file> -o <out_file>'
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print 'record.py -i <in_file> -o <out_file>'
            sys.exit()
        elif opt in ("-i", "--in_file"):
            in_file = arg
        elif opt in ("-o", "--out_file"):
            out_file = arg

    textfile = open(in_file, 'r')
    id = os.path.splitext(os.path.basename(in_file))[0]
    txt = textfile.read()
    textfile.close()
    record = make_record(txt, id)
    print record

if __name__ == "__main__":
    main(sys.argv[1:])