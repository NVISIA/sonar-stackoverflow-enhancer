import React from 'react';
import Select from 'react-select';

class SelectList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            selectedOption: '',
        };
    }

    handleChange = (selectedOption) => {
        this.setState({ selectedOption });
        // selectedOption can be null when the `x` (close) button is clicked
        if (selectedOption) {
            console.log(`Selected: ${selectedOption.label}`);
        }
    }
    render() {
        const { selectedOption } = this.state;

        return (
            <Select
                name="form-field-name"
                value={selectedOption}
                onChange={this.handleChange}
                options={this.props.values}
            />
        );
    }
}
export default SelectList;