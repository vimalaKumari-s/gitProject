import re
import sys
from sys import argv
import glob

def check(stack_dir):
    failure_count=0
    pattern="[a-z0-9]([-a-z0-9]*[a-z0-9])?(\.[a-z0-9]([-a-z0-9]*[a-z0-9])?)*"
    for a in glob.glob(stack_dir + "/alerts/instances/*.yaml"):
        lis=a.split("/")[-1].removesuffix(".yaml")
        print(lis)
        if not bool(re.fullmatch(pattern, lis)):
            print("[ERROR] Validation failed for " + a)
            failure_count=failure_count+1
    if failure_count>0:
        sys.exit(1)

stack_dir = argv[1]
check(stack_dir)

