import {render, screen} from '@testing-library/react';
import App from './App';

test('check for board to be present', () => {
    render(<App/>);

    const board = screen.getByRole("table", {class: "board"});

    expect(board.getElementsByClassName("row").length).toBe(3);

    Array.from(board.getElementsByClassName("row")).forEach((row) => {
        expect(row.getElementsByClassName("square").length).toBe(3);
    });

    expect(board).toBeInTheDocument();

});

test('check for board to be initially empty ', () => {
    render(<App/>);

    const board = screen.getByRole("table", {class: "board"});

    Array.from(board.getElementsByClassName("square")).forEach((square) => {
        expect(square.textContent).toBe("-");
    });
});

