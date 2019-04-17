import problems


def run_problem(n):
    pass

if __name__ == '__main__':
    import argparse
    parser = argparse.ArgumentParser()
    parser.add_argument('-a')
    parser.parse_args()

    print(dir(problems.problem001))
