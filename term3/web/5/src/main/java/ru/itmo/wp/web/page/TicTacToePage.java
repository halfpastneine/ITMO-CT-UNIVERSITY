package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {


    public class State {
        private final static int size = 3;
        private final String[][] cells;
        private final String phase;
        private final boolean crossesMove;


        public State(String phase, boolean crossesMove, String[][] cell) {
            this.phase = phase;
            this.cells = cell;
            this.crossesMove = crossesMove;
        }

        public int getSize() {
            return size;
        }

        public String[][] getCells() {
            return cells;
        }

        public String getPhase() {
            return phase;
        }

        public boolean isCrossesMove() {
            return crossesMove;
        }
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
        if (request.getSession().getAttribute("state") == null) {
            String[][] cell = new String[3][3];
            request.getSession().setAttribute("cells", cell);
            request.getSession().setAttribute("move", "X");
            request.getSession().setAttribute("result", "RUNNING");
            request.getSession().setAttribute("state", new State("RUNNING", true, cell));
            view.put("state", new State("RUNNING", true, cell));
        } else {
            view.put("state", request.getSession().getAttribute("state"));
        }

    }

    private void newGame(HttpServletRequest request, Map<String, Object> view) {
        request.getSession().invalidate();
        action(request, view);
    }

    private void onMove(HttpServletRequest request, Map<String, Object> view) {
        String[][] cell = (String[][]) request.getSession().getAttribute("cells");
        if (!request.getSession().getAttribute("result").equals("RUNNING")) {
            boolean q = request.getSession().getAttribute("move").equals("X");
            view.put("state", new State((String) request.getSession().getAttribute("result"), q, cell));
            return;
        }
        int x = 0, y = 0;
        for (Map.Entry<String, String[]> e : request.getParameterMap().entrySet()) {
            if (e.getKey().contains("cell")) {
                String[] q = e.getKey().split("_");
                try {
                    x = Integer.parseInt(String.valueOf(q[1].charAt(0)));
                    y = Integer.parseInt(String.valueOf(q[1].charAt(1)));
                    if (x >= State.size || y >= State.size) {
                        view.put("state", request.getSession().getAttribute("state"));
                    }
                } catch (NumberFormatException ignored){}
            }
        }
        boolean crossesMove = request.getSession().getAttribute("move").equals("X");
        if (cell[x][y] == null) {
            cell[x][y] = (String) request.getSession().getAttribute("move");
            if (request.getSession().getAttribute("move").equals("X")) {
                crossesMove = false;
                request.getSession().setAttribute("move", "O");
            } else {
                crossesMove = true;
                request.getSession().setAttribute("move", "X");
            }
        }
        boolean tr = checkVictory(cell[x][y], cell);
        if (tr) {
            request.getSession().setAttribute("state", new State("WON_" + cell[x][y], crossesMove, cell));
            view.put("state", new State("WON_" + cell[x][y], crossesMove, cell));
            request.getSession().setAttribute("result", "WON_" + cell[x][y]);
        } else {
            tr = checkDraw(cell);
            if (tr) {
                request.getSession().setAttribute("state", new State("DRAW", crossesMove, cell));
                view.put("state", new State("DRAW", crossesMove, cell));
                request.getSession().setAttribute("result", "DRAW");
            } else {
                request.getSession().setAttribute("state", new State("RUNNING", crossesMove, cell));
                view.put("state", new State("RUNNING", crossesMove, cell));
            }
        }

    }

    private boolean checkDraw(String[][] cell) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cell[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkVictory(String player, String[][] cell) {
        int count = 0;
        int count1 = 0;
        int count2 = 0;
        int count4 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cell[i][j] != null && cell[i][j].equals(player)) {
                    count++;
                }
                if (cell[j][i] != null && cell[j][i].equals(player)) {
                    count4++;
                }
                if (i + j == 2 && cell[i][j] != null && cell[i][j].equals(player)) {
                    count2++;
                }
            }
            if (cell[i][i] != null && cell[i][i].equals(player)) {
                count1++;
            }
            if (count == 3 || count1 == 3 || count2 == 3 || count4 == 3) {
                return true;
            }
            count = 0;
            count4 = 0;
        }
        return false;
    }
}