from problems import problem, all_problems
from time import time


def run_problems(problem_list):
    for p in problem_list:
        n = int(p.__name__[-3:])
        print('Solving problem {:d}'.format(n))
        start_t = time()
        ans = p.solve()
        end_t = time()
        print('  Answer: {}'.format(ans))
        if getattr(p, 'ANSWER', None):
            if p.ANSWER != ans:
                print('    Incorrect answer.')
        print('  Time: {:.0f} ms\n'.format((end_t - start_t) * 1000))


if __name__ == '__main__':
    import argparse
    parser = argparse.ArgumentParser()
    parser.add_argument('problems', help='problems to run',
                        nargs='*', type=int)
    args = parser.parse_args()

    if args.problems:
        p_list = []
        for n in args.problems:
            try:
                p_list.append(problem(n))
            except ModuleNotFoundError:
                print('Problem {:d} not found'.format(n))
    else:
        p_list = all_problems()
    run_problems(p_list)
