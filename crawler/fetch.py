#https://genealogy.math.ndsu.nodak.edu/id.php?id=169822
#python fetch.py --link https://genealogy.math.ndsu.nodak.edu/id.php?id= --id 169822 --outdir /Users/tanya/Data

import sys, getopt, urllib

def main(argv):
    link = ''
    id = ''
    outdir = ''
    try:
        opts, args = getopt.getopt(argv,"hl:i:o:",["link=","id=","outdir="])
    except getopt.GetoptError:
        print 'fetch.py -l <link> -i <id> -o <outdir>'
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print 'fetch.py -l <link> -i <id> -o <outdir>'
            sys.exit()
        elif opt in ("-l", "--link"):
            link = arg
        elif opt in ("-i", "--id"):
            id = arg
        elif opt in ("-o", "--outdir"):
            outdir = arg
    response = urllib.urlopen(link + id)
    html = response.read()
    of = open(outdir+"/"+id+".html", 'w')
    of.write(html)

if __name__ == "__main__":
    main(sys.argv[1:])