class Item extends Backbone.Model


class ItemView extends Backbone.View
  model: Item
  tagName: 'div'
  className: 'item'
  template: _.template($('#tmpl-item').html())

  render: ->
    $(@el).html(@template(item: @model))
    $(@el).addClass('item-completed') if @model.get('completed')
    @

  events: {
    'click': 'toggleCompleted'
  }

  $checkbox: -> $(@el).find('.item-toggle')

  toggleCompleted: ->
    $el       = $(@el)
    $checkbox = $el.find('.item-toggle')

    if @model.get('completed')
      @model.set('completed', false)
      $checkbox.prop('checked', false)
      $el.removeClass('item-completed')
    else
      @model.set('completed', true)
      $checkbox.prop('checked', true)
      $el.addClass('item-completed')



$ ->
  attr_list = [
    {name: 'test 1', trip_id: 2, completed: false},
    {name: 'test 2', trip_id: 2, completed: true},
  ]
  for attrs in attr_list
    view = new ItemView(model: new Item(attrs))
    $('.items-container').append(view.render().el)


