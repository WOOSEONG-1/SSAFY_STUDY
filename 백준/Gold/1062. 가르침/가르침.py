from itertools import combinations as comb
alpha = {'b', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l',
         'm', 'o', 'p', 'q', 'r', 's', 'u', 'v', 'w', 'x', 'y', 'z'}

n, m = map(int, input().split())
    
if m < 5:
    print(0)

else:
    m -= 5
    lst = []
    dic = {key: i for i, key in enumerate( (set(map(str, alpha) ))) }
    max_cnt = 0
        
    for _ in range(n):
        temp = 0
        for c in set(input()):
            if c in dic:
                temp |= (1 << dic[c])
        lst.append(temp)

    power_by_2 = (2**i for i in range(21))
    for i in comb(power_by_2, m):
        line = sum(i)
        cnt = 0
        for j in lst:
            if line & j == j:
                cnt += 1
        max_cnt = max(max_cnt, cnt)

    print(max_cnt)