#python crush.py --in_dir /Users/tanya/ProcessedResults --out_dir /Users/tanya/Crushed

import sys, getopt, glob, re, os, urllib, json
from pprint import pprint

def fixup(people, advisor_lookup):
    for student_id in advisor_lookup.keys():
        if student_id in people.keys():
            person = people[student_id]
            person["advisors"] = advisor_lookup[student_id]
            people[student_id] = person
    result = []
    for student_id in people.keys():
        person = people[student_id]
        if "advisors" not in person.keys():
            person["advisors"] = []
        result.append(person)
    return result

def load(data, people, advisor_lookup, file):
    id = data["id"]
    if int(id) == 0:
        #print file + " " + str(data)
        return
    name = data["name"]
    school = data["school"]
    flag = data["flag"]
    year = data["year"]
    people[id] = {"id": id,
                  "name": name,
                  "school": school,
                  "flag": flag,
                  "year": year}
    if len(data["students"]) > 0:
        for student in data["students"]:
            student_id = student["id"]
            advisors = []
            if student_id in advisor_lookup.keys():
                advisors = advisor_lookup[student_id]
            advisors.append(id)
            advisor_lookup[student_id] = advisors

def files(in_dir, out_dir):
    people = dict()
    advisor_lookup = dict()
    all_files = glob.glob(in_dir+"/*.json")
    i = 0
    for file in all_files:
        i = i + 1
        json_data=open(file).read()
        data = json.loads(json_data)
        load(data, people, advisor_lookup, file)
        if (i % 10000) == 0:
            print str(round(100.0*i/len(all_files))) + "%"
    people = fixup(people, advisor_lookup)
    out_file = out_dir + "/" + "all_math.json"
    with open(out_file, 'w') as fp:
        json.dump(people, fp)

def main(argv):
    in_dir = ''
    out_dir = ''
    try:
        opts, args = getopt.getopt(argv,"hi:o:",["in_dir=","out_dir="])
    except getopt.GetoptError:
        print 'crush.py -i <in_dir> -o <out_dir>'
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print 'crush.py -i <in_dir> -o <out_dir>'
            sys.exit()
        elif opt in ("-i", "--in_dir"):
            in_dir = arg
        elif opt in ("-o", "--out_dir"):
            out_dir = arg

    print in_dir
    print out_dir
    files(in_dir, out_dir)

if __name__ == "__main__":
    main(sys.argv[1:])