import re
import sys
from sys import argv
import glob

def check(stack_dir):
    failure_count=0
    regex= re.compile('[a-z0-9]([-a-z0-9]*[a-z0-9])?(\.[a-z0-9]([-a-z0-9]*[a-z0-9])?)*',re.I)
    for a in glob.glob("/Users/vimalakumari/Downloads/demo" + "/alerts/instances/*.yaml"):
        lis=a.split("/")[-1].removesuffix(".yaml")
        if not regex.match(lis):
            print("[ERROR] Validation failed for " + a)
            failure_count=failure_count+1
    if failure_count>0:
        sys.exit(1)

stack_dir = argv[1]
check(stack_dir)

