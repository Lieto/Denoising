#!/usr/bin/env python
"""
Diagram of Haar basis functions.
"""
__author__ = 'Emil Mikulic <emikulic@gmail.com>'
import Image
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.gridspec as gridspec

def inv_haar(a):
  scale = np.sqrt(2.)
  if len(a) == 1:
    return a.copy()
  assert len(a) % 2 == 0, "length needs to be even"
  mid = inv_haar(a[0:len(a)/2]) / 2 * scale
  side = a[len(a)/2:] / 2 * scale
  out = np.zeros(len(a), dtype=float)
  out[0::2] = mid + side
  out[1::2] = mid - side
  return out

def xy(a):
  """Returns arrays of x,y coords for plotting as bars."""
  x = [-.5]
  y = [0.]
  for idx, val in enumerate(a):
    x.append(idx - .5)
    y.append(val)
    x.append(idx + .5)
    y.append(val)
  x.append(len(a) - .5)
  y.append(0)
  return x,y

def main():
  n = 8
  out_fn = 'haar-basis.png'

  plt.rc('font', size=9)
  dpi = 72
  plot_width = 720.
  plot_height = 720.
  fig = plt.figure(figsize=(plot_width/dpi, plot_height/dpi), dpi=dpi)
  fig.patch.set(facecolor = 'white')
  gs = gridspec.GridSpec(n, 2, width_ratios = [1, 2])

  for i in range(n):
    a = np.zeros(n, dtype=float)
    a[i] = 1.
    b = inv_haar(a)

    energy = (b * b).sum()
    assert abs(energy - 1.) < 0.001

    ax = fig.add_subplot(gs[i, 0])
    ax.plot(*xy(a))
    ax.set_xlim(-.6, n - .4)
    ax.set_ylim(-.1, 1.1)
    if i == n - 1:
      ax.set_xlabel("one coefficient")

    ax = fig.add_subplot(gs[i, 1])
    ax.plot(*xy(b))
    ax.set_xlim(-.6, n - .4)
    ax.set_ylim(-.8, .8)
    if i == n - 1:
      ax.set_xlabel("corresponding Haar basis function")

  fig.tight_layout()
  fig.canvas.draw()
  w, h = fig.canvas.get_width_height()
  figimg = np.fromstring(fig.canvas.tostring_rgb(), dtype=np.uint8, sep='')
  Image.fromarray(figimg.reshape((h, w, 3))).save(out_fn)
  print 'wrote', out_fn

if __name__ == '__main__':
  main()

# vim:set ts=2 sw=2 et:
