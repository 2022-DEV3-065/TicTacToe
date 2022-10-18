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


test('check if square 0 is updated on click.', async () => {
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

test('check if other squares are updated on click.', async () => {
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


