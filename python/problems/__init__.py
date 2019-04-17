from importlib import import_module
from pathlib import Path
import re


def problem(n):
    n = int(n)
    return import_module('.problem{:03d}'.format(n), __name__)


def all_problems():
    path = Path(__file__).resolve().parent
    regex = re.compile(r'problem(\d{3})\.py')
    matches = (regex.match(f.name) for f in path.iterdir())
    probs = (m[1] for m in matches if m)
    return (problem(n) for n in probs)
