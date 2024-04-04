for t in range(1, 11):
    input()
    A = [[*map(int, input().split())] for _ in range(100)]
    cnt = 0
    for i in range(100):
        for j in range(99):
            if A[j][i] == 1:
                if A[j+1][i] != 0:
                    if A[j+1][i] == 2:
                        cnt += 1
                else:
                    A[j+1][i] = A[j][i]
    print(f"#{t} {cnt}")