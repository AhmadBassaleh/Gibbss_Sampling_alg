\ LP format - for model browsing. Use MPS format to capture full model detail.
Minimize
  785 Y00 + 1200 Y01 + 783 Y02 + 874 Y10 + 2000 Y11 + 3468 Y12
Subject To
 Const0: 12 X00 + 24 X10 + 15 X20 <= 50
 Const1: 12 X01 + 24 X11 + 15 X21 <= 25
 Const0: 2 X00 + 4 X10 + 3 X20 <= 100
 Const1: 2 X01 + 4 X11 + 3 X21 <= 120
 Const0: Y00 + Y10 <= 1
 Const1: Y01 + Y11 <= 1
 Const2: Y02 + Y12 <= 1
 Constr0: Y00 <= 1
 Constr1: Y01 <= 0
 Constr2: Y02 <= 1
 Constr0: Y10 <= 0
 Constr1: Y11 <= 1
 Constr2: Y12 <= 1
 ConstR0: X00 + X01 = 1
 ConstR1: X10 + X11 = 1
 ConstR2: X20 + X21 = 1
 Constraint00: Y00 + Y01 + Y02 - X00 >= 0
 Constraint01: Y00 + Y01 + Y02 - X10 >= 0
 Constraint02: Y00 + Y01 + Y02 - X20 >= 0
 Constraint10: Y10 + Y11 + Y12 - X01 >= 0
 Constraint11: Y10 + Y11 + Y12 - X11 >= 0
 Constraint12: Y10 + Y11 + Y12 - X21 >= 0
 Constraint0: Y00 + Y01 + Y02 <= 1
 Constraint1: Y10 + Y11 + Y12 <= 1
 Constraint00: Y00 + X00 <= 5
 Constraint10: Y10 + X01 <= 5
 Constraint01: Y01 + X00 <= 5
 Constraint11: Y11 + X01 <= 5
 Constraint02: Y02 + X00 <= 4
 Constraint12: Y12 + X01 <= 4
 Constraint00: Y00 + X10 <= 4
 Constraint10: Y10 + X11 <= 4
 Constraint01: Y01 + X10 <= 4
 Constraint11: Y11 + X11 <= 4
 Constraint02: Y02 + X10 <= 5
 Constraint12: Y12 + X11 <= 5
 Constraint00: Y00 + X20 <= 4
 Constraint10: Y10 + X21 <= 4
 Constraint01: Y01 + X20 <= 4
 Constraint11: Y11 + X21 <= 4
 Constraint02: Y02 + X20 <= 5
 Constraint12: Y12 + X21 <= 5
 Constraints00: 5 X00 + 2 X01 <= 5
 Constraints01: <= 5
 Constraints02: <= 5
 Constraints03: <= 5
 Constraints10: <= 4
 Constraints11: <= 4
 Constraints12: <= 4
 Constraints13: 4 X10 + 7 X11 <= 4
 Constraints20: <= 4
 Constraints21: 2 X20 + 4 X21 <= 4
 Constraints22: <= 4
 Constraints23: <= 4
Bounds
Binaries
 Y00 Y01 Y02 Y10 Y11 Y12 X00 X01 X10 X11 X20 X21
End
