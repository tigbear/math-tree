#python crawl.py --dir /Users/tanya/CrawlResults

import sys, getopt, glob, re, os, urllib

link = "https://genealogy.math.ndsu.nodak.edu/id.php?id="

def main(argv):
    dir = ''
    try:
        opts, args = getopt.getopt(argv,"hd:",["dir="])
    except getopt.GetoptError:
        print 'crawl.py -d <dir>'
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print 'crawl.py -d <dir>'
            sys.exit()
        elif opt in ("-d", "--dir"):
            dir = arg

    print dir
    [processed, unprocessed] = check_completness(dir)
    while(len(processed) != len(unprocessed)):
        for id in unprocessed:
            download(dir, id, link)
        [processed, unprocessed] = check_completness(dir)

def check_completness(dir):
    processed = []
    unprocessed = []
    all_files = glob.glob(dir+"/*.html")
    for filepath in all_files:
        id = os.path.splitext(os.path.basename(filepath))[0]
        if id not in processed:
            processed.append(id)
        mentioned = get_ids(filepath)
        for an_id in mentioned:
            if an_id not in processed:
                if an_id not in unprocessed:
                    unprocessed.append(an_id)
    print "Processed " + str(len(processed)) + " " + "Unprocessed " + str(len(unprocessed))
    return [processed, unprocessed]

def download(dir, id, link):
    response = urllib.urlopen(link + id)
    html = response.read()
    of = open(dir+"/"+id+".html", 'w')
    of.write(html)

def get_ids(file):
    textfile = open(file, 'r')
    filetext = textfile.read()
    textfile.close()
    regex = re.compile("id\.php\?id=(\d*)")
    matches = re.findall(regex, filetext)
    return matches

if __name__ == "__main__":
    main(sys.argv[1:])
