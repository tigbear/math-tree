
import re

filename = "/Users/tanya/Data/169822.html"
#<a href="id.php?id=84193">

textfile = open(filename, 'r')
filetext = textfile.read()
textfile.close()

#Advisor 1: <a href="id.php?id=23126">
regex = re.compile("id\.php\?id=(\d*)")
matches = re.findall(regex, filetext)
for match in matches:
    print match
#split = filetext.split("id.php?id=")
#print len(split)

