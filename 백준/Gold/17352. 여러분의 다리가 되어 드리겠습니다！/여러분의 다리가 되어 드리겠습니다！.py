def find(a):
    if a == parent[a]:
        return a
    else:
        parent[a] = find(parent[a])
        return parent[a]

def union(a, b):
    a = find(a)
    b = find(b)
    if a == b :
        return
    if a > b:
        parent[a] = b
    else:
        parent[b] = a

n = int(input())
parent = [ i for i in range(n+1) ]
for i in range(n-2):
    s, t = map(int,input().split())
    union(s, t)

for i in range(1, n+1):
    b = find(parent[i])
    if parent[1] != b:
        print(str(parent[1])+" "+str(b))
        break