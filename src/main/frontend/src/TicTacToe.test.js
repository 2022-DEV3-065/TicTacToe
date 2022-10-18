import {render, screen, waitFor} from '@testing-library/react';
import App from './App';
import {setupServer} from 'msw/node';
import {rest} from 'msw';
import userEvent from "@testing-library/user-event";


const server = setupServer();
beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());


test('check for board to be present', () => {
    render(<App/>);

    const board = screen.getByRole("table", {class: "board"});
    expect(board.getElementsByClassName("square").length).toBe(9);
    expect(board).toBeInTheDocument();

});

test('check for board to be initially empty ', () => {
    render(<App/>);

    const board = screen.getByRole("table", {class: "board"});

    Array.from(board.getElementsByClassName("square")).forEach((square) => {
        expect(square.textContent).toBe("-");
    });
});


test('check if square 0 is updated with X on click.', async () => {
    render(<App/>);

    server.use(
        rest.post('/logic', (req, res, ctx) => {

            const {state, squareClicked} = req.body;

            if (squareClicked === 0) {
                return res(ctx.json(
                    {
                        state: [
                            "X", "-", "-",
                            "-", "-", "-",
                            "-", "-", "-"
                        ]
                    }
                ));
            }
        })
    );

    const element = screen.getAllByRole("cell", {class: "square"})[0];
    userEvent.click(element);
    await waitFor(() => expect(screen.getAllByRole("cell", {class: "square"})[0].textContent).toBe("X"));
});

test('check if other squares are updated with X on first click.', async () => {
    render(<App/>);

    server.use(
        rest.post('/logic', (req, res, ctx) => {

            const {state, squareClicked} = req.body;

            if (squareClicked === 3) {
                return res(ctx.json(
                    {
                        state: [
                            "-", "-", "-",
                            "X", "-", "-",
                            "-", "-", "-"
                        ]
                    }
                ));
            }
        })
    );

    const element = screen.getAllByRole("cell", {class: "square"})[3];
    userEvent.click(element);
    await waitFor(() => expect(screen.getAllByRole("cell", {class: "square"})[3].textContent).toBe("X"));
});

test('check if second click is an O', async () => {
    render(<App/>);

    let emptyBoard = ["-", "-", "-", "-", "-", "-", "-", "-", "-"];

    server.use(
        rest.post('/logic', (req, res, ctx) => {

            const {state, squareClicked, turn} = req.body;

            //if board is empty, return with an X
            if (state.toString() === emptyBoard.toString()) {
                let boardWithXClicked = state.slice();
                boardWithXClicked[squareClicked] = turn;
                return res(ctx.json(
                    {
                        state: boardWithXClicked
                    }
                ));
            }

            //if not an empty board return with an O
            if (state !== emptyBoard) {
                let boardWith0Clicked = state.slice();
                boardWith0Clicked[squareClicked] = turn;
                return res(ctx.json(
                    {
                        state: boardWith0Clicked
                    }
                ));
            }
        })
    );

    //first click
    const element = screen.getAllByRole("cell", {class: "square"})[0];
    userEvent.click(element);

    //expect first click to be an X
    await waitFor(() => expect(screen.getAllByRole("cell", {class: "square"})[0].textContent).toBe("X"));

    //second click
    const secondElement = screen.getAllByRole("cell", {class: "square"})[3];
    userEvent.click(secondElement);

    //expect second click to be an O
    await waitFor(() => expect(screen.getAllByRole("cell", {class: "square"})[3].textContent).toBe("O"));
});

test('check if whose turn is displayed (First move)', () => {
    const {container} = render(<App/>);

    //check if X turn is displayed
    const turnText = container.getElementsByClassName("to-play");
    expect(turnText[0].textContent).toBe("To play: X");

    //no winner yet
    const winner = container.getElementsByClassName('winner');
    expect(winner.length).toBe(0);
});


test('check if whose turn is displayed (Second move)', async () => {
    const {container} = render(<App/>);
    let emptyBoard = ["-", "-", "-", "-", "-", "-", "-", "-", "-"];

    server.use(
        rest.post('/logic', (req, res, ctx) => {
            const {state, squareClicked, turn} = req.body;

            if (state.toString() === emptyBoard.toString()) {
                let boardWithXClicked = state.slice();
                boardWithXClicked[squareClicked] = turn;
                return res(ctx.json(
                    {
                        state: boardWithXClicked
                    }
                ));
            }
        })
    );

    const element = screen.getAllByRole("cell", {class: "square"})[3];
    userEvent.click(element);
    await waitFor(() => expect(screen.getAllByRole("cell", {class: "square"})[3].textContent).toBe("X"));

    //check if turn is displayed as O
    const turnText = container.getElementsByClassName("to-play");
    expect(turnText[0].textContent).toBe("To play: O");

    //no winner yet
    const winner = container.getElementsByClassName('winner');
    expect(winner.length).toBe(0);

});


test('O plays on played position with X', async () => {
    const {container} = render(<App/>);

    console.log = jest.fn();
    let emptyBoard = ["-", "-", "-", "-", "-", "-", "-", "-", "-"];

    server.use(
        rest.post('/logic', (req, res, ctx) => {

            const {state, squareClicked, turn} = req.body;

            if (state.toString() === emptyBoard.toString()) {
                let boardWithXClicked = state.slice();
                boardWithXClicked[squareClicked] = turn;
                return res(ctx.json(
                    {
                        state: boardWithXClicked
                    }
                ));
            }

            return res(
                ctx.status(400),
                ctx.json(
                    {
                        state: ["X", "-", "-", "-", "-", "-", "-", "-", "-"],
                        error: "Square already played"
                    }
                ));
        })
    );

    //first click on same square
    let element = screen.getAllByRole("cell", {class: "square"})[0];
    userEvent.click(element);

    //wait for it to be X
    await waitFor(() => expect(screen.getAllByRole("cell", {class: "square"})[0].textContent).toBe("X"));

    //refetch the square after rendering
    element = screen.getAllByRole("cell", {class: "square"})[0];

    //second click on same square
    userEvent.click(element);

    //error should be logged
    await waitFor(() => expect(console.log).toHaveBeenCalledWith("Square already played"));

    //square should still be X
    await waitFor(() => expect(screen.getAllByRole("cell", {class: "square"})[0].textContent).toBe("X"));

    //turn should still be O
    const turnText = container.getElementsByClassName("to-play");
    expect(turnText[0].textContent).toBe("To play: O");

});


test('check when X is a winner', async () => {
    const {container} = render(<App/>);

    server.use(
        rest.post('/logic', (req, res, ctx) => {
            return res(ctx.json(
                {
                    state: [
                        "X", "-", "O",
                        "O", "X", "-",
                        "O", "-", "X"
                    ],
                    winner: "X"
                }
            ));
        })
    );

    const element = screen.getAllByRole("cell", {class: "square"})[0];
    userEvent.click(element);

    await waitFor(() => expect(screen.getAllByRole("cell", {class: "square"})[0].textContent).toBe("X"));

    //winner should be X
    const winnerText = container.getElementsByClassName("winner");
    expect(winnerText[0].textContent).toBe("Winner: X");

    //turn should not be displayed
    const toPlay = container.getElementsByClassName('to-play');
    expect(toPlay.length).toBe(0);

    //reset button should be present
    const reset = container.getElementsByClassName('reset');
    expect(reset.length).toBe(1);

});


test('check when O is a winner', async () => {
    const {container} = render(<App/>);

    server.use(
        rest.post('/logic', (req, res, ctx) => {
            return res(ctx.json(
                {
                    state: [
                        "O", "-", "X",
                        "O", "X", "-",
                        "O", "-", "X"
                    ],
                    winner: "O"
                }
            ));
        })
    );

    const element = screen.getAllByRole("cell", {class: "square"})[0];
    userEvent.click(element);

    await waitFor(() => expect(screen.getAllByRole("cell", {class: "square"})[0].textContent).toBe("O"));

    //winner should be O
    const winnerText = container.getElementsByClassName("winner");
    expect(winnerText[0].textContent).toBe("Winner: O");

    //turn should not be displayed
    const toPlay = container.getElementsByClassName('to-play');
    expect(toPlay.length).toBe(0);

    //reset button should be present
    const reset = container.getElementsByClassName('reset');
    expect(reset.length).toBe(1);
});


test('check for a draw', async () => {
    const {container} = render(<App/>);

    server.use(
        rest.post('/logic', (req, res, ctx) => {
            return res(ctx.json(
                {
                    state: [
                        "O", "O", "X",
                        "X", "X", "O",
                        "O", "X", "X"
                    ],
                    winner: "DRAW"
                }
            ));
        })
    );

    const element = screen.getAllByRole("cell", {class: "square"})[0];
    userEvent.click(element);

    await waitFor(() => expect(screen.getAllByRole("cell", {class: "square"})[0].textContent).toBe("O"));

    //check for draw text
    const winnerText = container.getElementsByClassName("winner");
    expect(winnerText[0].textContent).toBe("Draw");

    //turn should not be displayed
    const toPlay = container.getElementsByClassName('to-play');
    expect(toPlay.length).toBe(0);

    //reset button should be present
    const reset = container.getElementsByClassName('reset');
    expect(reset.length).toBe(1);
});


test('check for board being unlickable when there is a draw', async () => {
    const {container} = render(<App/>);
    console.log = jest.fn();

    server.use(
        rest.post('/logic', (req, res, ctx) => {
            return res(ctx.json(
                {
                    state: [
                        "O", "O", "X",
                        "X", "X", "O",
                        "O", "X", "X"
                    ],
                    winner: "DRAW"
                }
            ));
        })
    );

    const element = screen.getAllByRole("cell", {class: "square"})[0];
    userEvent.click(element);

    await waitFor(() => expect(screen.getAllByRole("cell", {class: "square"})[0].textContent).toBe("O"));

    const winnerText = container.getElementsByClassName("winner");
    expect(winnerText[0].textContent).toBe("Draw");

    //click on a square
    userEvent.click(screen.getAllByRole("cell", {class: "square"})[1]);

    //game over message should be logged
    await waitFor(() => expect(console.log).toHaveBeenCalledWith("Game over"));

    //turn should not be displayed
    const toPlay = container.getElementsByClassName('to-play');
    expect(toPlay.length).toBe(0);

    //reset button should be present
    const reset = container.getElementsByClassName('reset');
    expect(reset.length).toBe(1);
});


test('check for board being unlickable when there is a winner', async () => {
    const {container} = render(<App/>);
    console.log = jest.fn();

    server.use(
        rest.post('/logic', (req, res, ctx) => {
            return res(ctx.json(
                {
                    state: [
                        "X", "O", "-",
                        "O", "X", "O",
                        "-", "-", "X"
                    ],
                    winner: "X"
                }
            ));
        })
    );

    const element = screen.getAllByRole("cell", {class: "square"})[0];
    userEvent.click(element);

    await waitFor(() => expect(screen.getAllByRole("cell", {class: "square"})[0].textContent).toBe("X"));

    const winnerText = container.getElementsByClassName("winner");
    expect(winnerText[0].textContent).toBe("Winner: X");

    //click on a square
    userEvent.click(screen.getAllByRole("cell", {class: "square"})[1]);

    //game over message should be logged
    await waitFor(() => expect(console.log).toHaveBeenCalledWith("Game over"));

    //turn should not be displayed
    const toPlay = container.getElementsByClassName('to-play');
    expect(toPlay.length).toBe(0);

    //reset button should be present
    const reset = container.getElementsByClassName('reset');
    expect(reset.length).toBe(1);
});

test('reset board', async () => {
    const {container} = render(<App/>);

    server.use(
        rest.post('/logic', (req, res, ctx) => {
            return res(ctx.json(
                {
                    state: [
                        "X", "-", "O",
                        "O", "X", "-",
                        "O", "-", "X"
                    ],
                    winner: "X"
                }
            ));
        })
    );

    const element = screen.getAllByRole("cell", {class: "square"})[0];
    userEvent.click(element);

    await waitFor(() => expect(screen.getAllByRole("cell", {class: "square"})[0].textContent).toBe("X"));

    const winnerText = container.getElementsByClassName("winner");
    expect(winnerText[0].textContent).toBe("Winner: X");

    //click on reset button
    userEvent.click(screen.getByRole("button", {class: "reset"}));

    //board should be empty
    await waitFor(() => expect(screen.getAllByRole("cell", {class: "square"})[0].textContent).toBe("-"));

    //turn should be X
    const turnText = container.getElementsByClassName("to-play");
    expect(turnText[0].textContent).toBe("To play: X");

    //no winner yet
    const winner = container.getElementsByClassName('winner');
    expect(winner.length).toBe(0);

});
