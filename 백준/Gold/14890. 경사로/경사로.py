MV = 10000000
for t in range(1, 2):
    n, x = map(int, input().split())
    A = [[*map(int, input().split())] for _ in range(n)]
    cnt = 0
    for i in range(n):
        if t == 1 and i == 3:
            t = 1
        chk = -1
        ramp = 0
        for j in range(n-1):
            if A[i][j] == A[i][j+1] - 1:
                if x-1 <= j:
                    for k in range(x):
                        if A[i][j] != A[i][j-k] or j-k <= chk:
                            ramp = MV
                            break
                else:
                    ramp = MV
                    break
            elif A[i][j] == A[i][j+1] + 1:
                if j <= n-x-1:
                    for k in range(x):
                        if A[i][j+1] != A[i][j+1+k]:
                            ramp = MV
                            break
                        if ramp != MV:
                            chk = j+x
                else:
                    ramp = MV
                    break
            elif abs(A[i][j] - A[i][j+1]) > 1:
                ramp = MV
                break
        if ramp == 0:
            cnt += 1
        chk = -1
        ramp = 0
        for j in range(n - 1):
            if A[j][i] == A[j+1][i] - 1:
                if x - 1 <= j:
                    for k in range(x):
                        if A[j][i] != A[j-k][i] or j-k <= chk:
                            ramp = MV
                            break
                else:
                    ramp = MV
                    break
            elif A[j][i] == A[j+1][i] + 1:
                if j <= n - x - 1:
                    for k in range(x):
                        if A[j+1][i] != A[j+1+k][i]:
                            ramp = MV
                            break
                    if ramp != MV:
                        chk = j+x
                else:
                    ramp = MV
                    break
            elif abs(A[j][i] - A[j+1][i]) > 1:
                ramp = MV
                break
        if ramp == 0:
            cnt += 1
    print(f"{cnt}")