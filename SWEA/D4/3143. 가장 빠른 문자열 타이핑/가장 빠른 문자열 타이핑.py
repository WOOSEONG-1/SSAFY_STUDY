def match(txt, pat):
    cnt = 0
    idx = 0
    ptr = 0
    while idx < len(txt)-len(pat)+1:
        for i in range(len(pat)):
            if txt[idx+i] != pat[i]:
                break
        else:
            cnt += 1
            idx += len(pat)
            continue
        idx += 1
    return cnt
for t in range(1, int(input()) + 1):
    txt, pat = input().split()
    print(f'#{t} {len(txt) - (len(pat)-1) * match(txt, pat)}')