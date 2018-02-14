import json
import collections

r = open('sanitized.txt', 'r')
s = open('sanitized_movied_info.txt', 'r')

a = []
b = []

for line in r:
    a.append(json.loads(line)['imdbID'])

for line in s:
    b.append(line.split('::')[1])

print(a)
print(b)

print(len(a), len(b))

# print(([item for item, count in collections.Counter(a).items() if count > 1]))

diff = list(set(b) - set(a))

print(diff)

r.close()
s.close()



# lines_seen = set() # holds lines already seen
# outfile = open('sanitized_movied_info.txt', "w")
# for line in open('movie_info.txt', "r"):
#     if line not in lines_seen: # not a duplicate
#         outfile.write(line)
#         lines_seen.add(line)
# outfile.close()
